/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinematrix.data;

import cinematrix.logic.Sala;
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
public class SalaDAO implements DAO<String, Sala> {
    
    private SalaDAO() {
        try {
            System.out.println("Configurando acceso a la base de datos..");
            bd = BaseDatos.obtenerInstancia();
        } catch (IOException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
            System.exit(-1);
        }
    }
    
    public static SalaDAO obtenerInstancia() {
        if (instancia == null) {
            instancia = new SalaDAO();
        }
        return instancia;
    }
    
    @Override
    public List<Sala> listarTodos() {
        List<Sala> salas = new ArrayList<>();
        try (Connection cnx = bd.getConnection();
                Statement stm = cnx.createStatement();
                ResultSet rs = stm.executeQuery(SalaCRUD.LISTAR)) {
            while (rs.next()) {
                salas.add(new Sala(
                        rs.getString("numero_Sala"),
                        rs.getInt("numero_butacas")
                ));
            }
        } catch (SQLException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
        }
        return salas;
    }
    
    @Override
    public void agregar(String id, Sala nuevaSala) {
        try (Connection cnx = bd.getConnection();
            PreparedStatement stm = cnx.prepareStatement(SalaCRUD.AGREGAR)) {
            stm.clearParameters();
            stm.setString(1, nuevaSala.getNumero_Sala());
            stm.setInt(2, nuevaSala.getNumero_butacas());
            if (stm.executeUpdate() != 1) {
                throw new IllegalArgumentException(
                        String.format("No se pudo agregar el registro: '%s'",
                                nuevaSala.getNumero_Sala()));
            }
        } catch (IllegalArgumentException | SQLException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
            throw new IllegalArgumentException(ex.getMessage());
        }
    }
    
    public void agregar(Sala s) {
        agregar(s.getNumero_Sala(), s);
    }
    
    @Override
    public Sala recuperar(String numero_sala) {
        Sala sala = null;
        try {
            try (Connection cnx = bd.getConnection();
                    PreparedStatement stm = cnx.prepareStatement(SalaCRUD.RECUPERAR)) {
                stm.clearParameters();
                stm.setString(1, numero_sala);
                try (ResultSet rs = stm.executeQuery()) {
                    if (rs.next()) {
                        sala = new Sala(
                                rs.getString("numero_Sala"),
                                rs.getInt("numero_butacas")
                        );
                    }
                }
            }
        } catch (SQLException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
        }
        return sala;
    }
    
    @Override
    public void actualizar(String id, Sala sala) {
        try (Connection cnx = bd.getConnection();
                PreparedStatement stm = cnx.prepareStatement(SalaCRUD.ACTUALIZAR)) {
            stm.clearParameters();
            stm.setString(1, sala.getNumero_Sala());
            stm.setInt(2, sala.getNumero_butacas());
            stm.setString(3, sala.getNumero_Sala());
            if (stm.executeUpdate() != 1) {
                throw new IllegalArgumentException(
                        String.format("No se pudo actualizar el registro: '%s'",
                                sala.getNumero_Sala()));
            }
        } catch (IllegalArgumentException | SQLException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
            throw new IllegalArgumentException(ex.getMessage());
        }
    }

    public void actualizar(Sala s) {
        actualizar(s.getNumero_Sala(), s);
    }
    
    @Override
    public void eliminar(String numero_sala) {
        try {
            try (Connection cnx = bd.getConnection();
                    PreparedStatement stm = cnx.prepareStatement(SalaCRUD.ELIMINAR)) {
                stm.clearParameters();
                stm.setString(1, numero_sala);
                if (stm.executeUpdate() != 1) {
                    throw new IllegalArgumentException();
                }
            }
        } catch (SQLException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
            throw new IllegalArgumentException(ex.getMessage());
        }
    }
    
    private static SalaDAO instancia = null;
    private BaseDatos bd;
}
