/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinematrix.data;

import cinematrix.logic.Funcion;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author andre
 */
public class FuncionDAO implements DAO<String, Funcion> {

    private FuncionDAO() {
        try {
            System.out.println("Configurando acceso a la base de datos..");
            bd = BaseDatos.obtenerInstancia();
        } catch (IOException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
            System.exit(-1);
        }
    }

    public static FuncionDAO obtenerInstancia() {
        if (instancia == null) {
            instancia = new FuncionDAO();
        }
        return instancia;
    }

    @Override
    public List<Funcion> listarTodos() {
        List<Funcion> funciones = new ArrayList<>();
        try (Connection cnx = bd.getConnection();
                Statement stm = cnx.createStatement();
                ResultSet rs = stm.executeQuery(FuncionCRUD.LISTAR)) {
            while (rs.next()) {
                funciones.add(new Funcion(
                        rs.getInt("f.codigo_funcion"),
                        rs.getString("p.nombre"),
                        rs.getString("f.Sala_numero_Sala"),
                        rs.getString("f.fecha"),
                        rs.getFloat("f.precio")
                ));
            }
        } catch (SQLException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
        }
        return funciones;
    }

    @Override
    public void agregar(String id, Funcion nuevaFuncion) {
        try (Connection cnx = bd.getConnection();
                PreparedStatement stm = cnx.prepareStatement(FuncionCRUD.AGREGAR)) {
            stm.clearParameters();
            stm.setInt(1, Integer.parseInt(nuevaFuncion.getPelicula()));
            stm.setString(2, nuevaFuncion.getSala());
            stm.setString(3, nuevaFuncion.getFecha());
            stm.setFloat(4, nuevaFuncion.getPrecio());
            if (stm.executeUpdate() != 1) {
                throw new IllegalArgumentException(
                        String.format("No se pudo agregar el registro: '%s'",
                                nuevaFuncion.getCodigo()));
            }
        } catch (IllegalArgumentException | SQLException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
            throw new IllegalArgumentException(ex.getMessage());
        }
    }

    public void agregar(Funcion f) {
        agregar(String.valueOf(f.getCodigo()), f);
    }

    @Override
    public Funcion recuperar(String codigo) {
        Funcion funcion = null;
        try {
            try (Connection cnx = bd.getConnection();
                    PreparedStatement stm = cnx.prepareStatement(FuncionCRUD.RECUPERAR)) {
                stm.clearParameters();
                stm.setString(1, codigo);
                try (ResultSet rs = stm.executeQuery()) {
                    if (rs.next()) {
                        funcion = new Funcion(
                                rs.getInt("f.codigo_funcion"),
                                rs.getString("p.nombre"),
                                rs.getString("f.Sala_numero_Sala"),
                                rs.getString("f.fecha"),
                                rs.getFloat("f.precio")
                        );
                    }
                }
            }
        } catch (SQLException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
        }
        return funcion;
    }

    @Override
    public void actualizar(String id, Funcion funcion) {
        try (Connection cnx = bd.getConnection();
                PreparedStatement stm = cnx.prepareStatement(FuncionCRUD.ACTUALIZAR)) {
            stm.clearParameters();
            stm.setInt(1, funcion.getCodigo());
            stm.setInt(2, Integer.parseInt(funcion.getPelicula()));
            stm.setString(3, funcion.getSala());
            stm.setDate(4, Date.valueOf(funcion.getFecha()));
            stm.setFloat(5, funcion.getPrecio());
            stm.setInt(5, funcion.getCodigo());
            if (stm.executeUpdate() != 1) {
                throw new IllegalArgumentException(
                        String.format("No se pudo actualizar el registro: '%s'",
                                funcion.getCodigo()));
            }
        } catch (IllegalArgumentException | SQLException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
            throw new IllegalArgumentException(ex.getMessage());
        }
    }

    public void actualizar(Funcion f) {
        actualizar(String.valueOf(f.getCodigo()), f);
    }

    @Override
    public void eliminar(String codigo) {
        try {
            try (Connection cnx = bd.getConnection();
                    PreparedStatement stm = cnx.prepareStatement(FuncionCRUD.ELIMINAR)) {
                stm.clearParameters();
                stm.setString(1, codigo);
                if (stm.executeUpdate() != 1) {
                    throw new IllegalArgumentException();
                }
            }
        } catch (SQLException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
            throw new IllegalArgumentException(ex.getMessage());
        }
    }

    private static FuncionDAO instancia = null;
    private BaseDatos bd;
}
