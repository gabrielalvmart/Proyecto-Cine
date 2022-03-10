/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinematrix.data;

import cinematrix.logic.Pelicula;
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
public class PeliculaDAO implements DAO<String, Pelicula> {
 
    private PeliculaDAO() {
        try {
            System.out.println("Configurando acceso a la base de datos..");
            bd = BaseDatos.obtenerInstancia();
        } catch (IOException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
            System.exit(-1);
        }
    }
    
    public static PeliculaDAO obtenerInstancia() {
        if (instancia == null) {
            instancia = new PeliculaDAO();
        }
        return instancia;
    }
    
    @Override
    public List<Pelicula> listarTodos() {
        List<Pelicula> peliculas = new ArrayList<>();
        try (Connection cnx = bd.getConnection();
                Statement stm = cnx.createStatement();
                ResultSet rs = stm.executeQuery(PeliculaCRUD.LISTAR)) {
            while (rs.next()) {
                peliculas.add(new Pelicula(
                        rs.getInt("codigo"),
                        rs.getString("nombre"),
                        rs.getString("genero"),
                        rs.getFloat("precio")
                ));
            }
        } catch (SQLException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
        }
        return peliculas;
    }
    
    @Override
    public void agregar(String id, Pelicula nuevaPelicula) {
        try (Connection cnx = bd.getConnection();
            PreparedStatement stm = cnx.prepareStatement(PeliculaCRUD.AGREGAR)) {
            stm.clearParameters();
            stm.setString(1, nuevaPelicula.getNombre());
            stm.setString(2, nuevaPelicula.getGenero());
            stm.setFloat(3, nuevaPelicula.getPrecio());
            if (stm.executeUpdate() != 1) {
                throw new IllegalArgumentException(
                        String.format("No se pudo agregar el registro: '%s'",
                                nuevaPelicula.getCodigo()));
            }
        } catch (IllegalArgumentException | SQLException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
            throw new IllegalArgumentException(ex.getMessage());
        }
    }
    
    public void agregar(Pelicula p) {
        agregar(String.valueOf(p.getCodigo()), p);
    }
    
    @Override
    public Pelicula recuperar(String codigo) {
        Pelicula pelicula = null;
        try {
            try (Connection cnx = bd.getConnection();
                    PreparedStatement stm = cnx.prepareStatement(PeliculaCRUD.RECUPERAR)) {
                stm.clearParameters();
                stm.setString(1, codigo);
                try (ResultSet rs = stm.executeQuery()) {
                    if (rs.next()) {
                        pelicula = new Pelicula(
                                rs.getInt("codigo"),
                                rs.getString("nombre"),
                                rs.getString("genero"),
                                rs.getInt("precio")
                        );
                    }
                }
            }
        } catch (SQLException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
        }
        return pelicula;
    }
    
    @Override
    public void actualizar(String id, Pelicula pelicula) {
        try (Connection cnx = bd.getConnection();
                PreparedStatement stm = cnx.prepareStatement(PeliculaCRUD.ACTUALIZAR)) {
            stm.clearParameters();
            stm.setInt(1, pelicula.getCodigo());
            stm.setString(2, pelicula.getNombre());
            stm.setString(3, pelicula.getGenero());
            stm.setFloat(4, pelicula.getPrecio());
            stm.setInt(5, pelicula.getCodigo());
            if (stm.executeUpdate() != 1) {
                throw new IllegalArgumentException(
                        String.format("No se pudo actualizar el registro: '%s'",
                                pelicula.getCodigo()));
            }
        } catch (IllegalArgumentException | SQLException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
            throw new IllegalArgumentException(ex.getMessage());
        }
    }

    public void actualizar(Pelicula p) {
        actualizar(String.valueOf(p.getCodigo()), p);
    }
    
    @Override
    public void eliminar(String codigo) {
        try {
            try (Connection cnx = bd.getConnection();
                    PreparedStatement stm = cnx.prepareStatement(PeliculaCRUD.ELIMINAR)) {
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
    
    private static PeliculaDAO instancia = null;
    private BaseDatos bd;
}
