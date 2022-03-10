/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinematrix.data;

import cinematrix.logic.Sala;
import cinematrix.logic.Tiquete;
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
public class TiqueteDAO implements DAO<String, Tiquete>{
    private TiqueteDAO() {
        try {
            System.out.println("Configurando acceso a la base de datos..");
            bd = BaseDatos.obtenerInstancia();
        } catch (IOException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
            System.exit(-1);
        }
    }
    
    public static TiqueteDAO obtenerInstancia() {
        if (instancia == null) {
            instancia = new TiqueteDAO();
        }
        return instancia;
    }
    
    @Override
    public List<Tiquete> listarTodos() {
        List<Tiquete> tiquetes = new ArrayList<>();
        try (Connection cnx = bd.getConnection();
                Statement stm = cnx.createStatement();
                ResultSet rs = stm.executeQuery(TiqueteCRUD.LISTAR)) {
            while (rs.next()) {
                tiquetes.add(new Tiquete(
                        rs.getInt("codigo_tiquete"),
                        UsuarioDAO.obtenerInstancia().recuperar(rs.getString("Usuario_cedula")),
                        FuncionDAO.obtenerInstancia().recuperar(rs.getString("Funcion_codigo_funcion")),
                        rs.getString("butacas")
                ));
            }
        } catch (SQLException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
        }
        return tiquetes;
    }
    
    @Override
    public void agregar(String id, Tiquete nuevoTiquete) {
        try (Connection cnx = bd.getConnection();
            PreparedStatement stm = cnx.prepareStatement(TiqueteCRUD.AGREGAR)) {
            stm.clearParameters();
            stm.setString(1, nuevoTiquete.getUsuario().getCedula());
            stm.setInt(2, nuevoTiquete.getFuncion().getCodigo());
            stm.setString(3, nuevoTiquete.getButacas());
            if (stm.executeUpdate() != 1) {
                throw new IllegalArgumentException(
                        String.format("No se pudo agregar el registro: '%s'",
                                nuevoTiquete.getUsuario().getCedula()));
            }
        } catch (IllegalArgumentException | SQLException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
            throw new IllegalArgumentException(ex.getMessage());
        }
    }
    
    public void agregar(Tiquete t) {
        agregar(String.valueOf(t.getCodigo()), t);
    }
    
    @Override
    public Tiquete recuperar(String codigo_tiquete) {
        Tiquete tiquete = null;
        try {
            try (Connection cnx = bd.getConnection();
                    PreparedStatement stm = cnx.prepareStatement(TiqueteCRUD.RECUPERAR)) {
                stm.clearParameters();
                stm.setString(1, codigo_tiquete);
                try (ResultSet rs = stm.executeQuery()) {
                    if (rs.next()) {
                        tiquete = new Tiquete(
                                rs.getInt("codigo_tiquete"),
                                UsuarioDAO.obtenerInstancia().recuperar(rs.getString("Usuario_cedula")),
                                FuncionDAO.obtenerInstancia().recuperar(rs.getString("Funcion_codigo_funcion")),
                                rs.getString("butacas")
                        );
                    }
                }
            }
        } catch (SQLException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
        }
        return tiquete;
    }
    
    @Override
    public void actualizar(String id, Tiquete tiquete) {
        try (Connection cnx = bd.getConnection();
                PreparedStatement stm = cnx.prepareStatement(TiqueteCRUD.ACTUALIZAR)) {
            stm.clearParameters();
            stm.setInt(1, tiquete.getCodigo());
            stm.setString(2, tiquete.getUsuario().getCedula());
            stm.setInt(3, tiquete.getFuncion().getCodigo());
            stm.setString(4, tiquete.getButacas());
            stm.setInt(5, tiquete.getCodigo());
            if (stm.executeUpdate() != 1) {
                throw new IllegalArgumentException(
                        String.format("No se pudo actualizar el registro: '%s'",
                                tiquete.getCodigo()));
            }
        } catch (IllegalArgumentException | SQLException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
            throw new IllegalArgumentException(ex.getMessage());
        }
    }

    public void actualizar(Tiquete t) {
        actualizar(String.valueOf(t.getCodigo()), t);
    }
    
    @Override
    public void eliminar(String codigo_tiquete) {
        try {
            try (Connection cnx = bd.getConnection();
                    PreparedStatement stm = cnx.prepareStatement(TiqueteCRUD.ELIMINAR)) {
                stm.clearParameters();
                stm.setString(1, codigo_tiquete);
                if (stm.executeUpdate() != 1) {
                    throw new IllegalArgumentException();
                }
            }
        } catch (SQLException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
            throw new IllegalArgumentException(ex.getMessage());
        }
    }
    
    private static TiqueteDAO instancia = null;
    private BaseDatos bd;
}
