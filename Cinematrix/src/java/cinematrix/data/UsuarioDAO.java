/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinematrix.data;

import cinematrix.logic.Usuario;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author andre
 */
public class UsuarioDAO implements DAO<String, Usuario> {

    private UsuarioDAO() {
        try {
            System.out.println("Configurando acceso a la base de datos..");
            bd = BaseDatos.obtenerInstancia();
        } catch (IOException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
            System.exit(-1);
        }
    }

    public static UsuarioDAO obtenerInstancia() {
        if (instancia == null) {
            instancia = new UsuarioDAO();
        }
        return instancia;
    }

    @Override
    public List<Usuario> listarTodos() {
        List<Usuario> usuarios = new ArrayList<>();
        try (Connection cnx = bd.getConnection();
                Statement stm = cnx.createStatement();
                ResultSet rs = stm.executeQuery(UsuarioCRUD.LISTAR)) {
            while (rs.next()) {
                usuarios.add(new Usuario(
                        rs.getString("cedula"),
                        rs.getString("nombre"),
                        rs.getString("contraseña"),
                        rs.getString("perfil")
                ));
            }
        } catch (SQLException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
        }
        return usuarios;
    }

    @Override
    public void agregar(String id, Usuario nuevoUsuario) {
        try (Connection cnx = bd.getConnection();
                PreparedStatement stm = cnx.prepareStatement(UsuarioCRUD.AGREGAR)) {
            stm.clearParameters();
            stm.setString(1, nuevoUsuario.getCedula());
            stm.setString(2, nuevoUsuario.getNombre());
            stm.setString(3, nuevoUsuario.getPassword());
            stm.setString(4, nuevoUsuario.getPerfil());
            if (stm.executeUpdate() != 1) {
                throw new IllegalArgumentException(
                        String.format("No se pudo agregar el registro: '%s'",
                                nuevoUsuario.getCedula()));
            }
        } catch (IllegalArgumentException | SQLException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
            throw new IllegalArgumentException(ex.getMessage());
        }
    }

    public void agregar(Usuario p) {
        agregar(p.getCedula(), p);
    }

    @Override
    public Usuario recuperar(String cedula) {
        Usuario usuario = null;
        try {
            try (Connection cnx = bd.getConnection();
                    PreparedStatement stm = cnx.prepareStatement(UsuarioCRUD.RECUPERAR)) {
                stm.clearParameters();
                stm.setString(1, cedula);
                try (ResultSet rs = stm.executeQuery()) {
                    if (rs.next()) {
                        usuario = new Usuario(
                                rs.getString("cedula"),
                                rs.getString("nombre"),
                                rs.getString("contraseña"),
                                rs.getString("perfil")
                        );
                    }
                }
            }
        } catch (SQLException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
        }
        return usuario;
    }

    @Override
    public void actualizar(String id, Usuario usuario) {
        try (Connection cnx = bd.getConnection();
                PreparedStatement stm = cnx.prepareStatement(UsuarioCRUD.ACTUALIZAR)) {
            stm.clearParameters();
            stm.setString(1, usuario.getCedula());
            stm.setString(2, usuario.getNombre());
            stm.setString(3, usuario.getPassword());
            stm.setString(4, usuario.getPerfil());
            stm.setString(5, usuario.getCedula());
            if (stm.executeUpdate() != 1) {
                throw new IllegalArgumentException(
                        String.format("No se pudo actualizar el registro: '%s'",
                                usuario.getCedula()));
            }
        } catch (IllegalArgumentException | SQLException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
            throw new IllegalArgumentException(ex.getMessage());
        }
    }

    public void actualizar(Usuario p) {
        actualizar(p.getCedula(), p);
    }

    @Override
    public void eliminar(String cedula) {
        try {
            try (Connection cnx = bd.getConnection();
                    PreparedStatement stm = cnx.prepareStatement(UsuarioCRUD.ELIMINAR)) {
                stm.clearParameters();
                stm.setString(1, cedula);
                if (stm.executeUpdate() != 1) {
                    throw new IllegalArgumentException();
                }
            }
        } catch (SQLException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
            throw new IllegalArgumentException(ex.getMessage());
        }
    }

    private static UsuarioDAO instancia = null;
    private BaseDatos bd;
}
