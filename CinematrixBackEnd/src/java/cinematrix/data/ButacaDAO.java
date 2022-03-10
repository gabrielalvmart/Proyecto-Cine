/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinematrix.data;

import cinematrix.logic.Butaca;
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
public class ButacaDAO implements DAO<String, Butaca>{
    private ButacaDAO() {
        try {
            System.out.println("Configurando acceso a la base de datos..");
            bd = BaseDatos.obtenerInstancia();
        } catch (IOException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
            System.exit(-1);
        }
    }
    
    public static ButacaDAO obtenerInstancia() {
        if (instancia == null) {
            instancia = new ButacaDAO();
        }
        return instancia;
    }
    
    @Override
    public List<Butaca> listarTodos() {
        List<Butaca> butacas = new ArrayList<>();
        try (Connection cnx = bd.getConnection();
                Statement stm = cnx.createStatement();
                ResultSet rs = stm.executeQuery(ButacaCRUD.LISTAR)) {
            while (rs.next()) {
                butacas.add(new Butaca(
                        rs.getInt("numero_butaca"),
                        rs.getBoolean("estado"),
                        FuncionDAO.obtenerInstancia().recuperar(rs.getString("funcion_codigo_funcion"))
                ));
            }
        } catch (SQLException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
        }
        return butacas;
    }
    
    @Override
    public void agregar(String id, Butaca nuevaButaca) {
        try (Connection cnx = bd.getConnection();
            PreparedStatement stm = cnx.prepareStatement(ButacaCRUD.AGREGAR)) {
            stm.clearParameters();
            stm.setInt(1, nuevaButaca.getNumero_butaca());
            stm.setBoolean(2, nuevaButaca.isEstado());
            stm.setInt(3, nuevaButaca.getFuncion().getCodigo());
            if (stm.executeUpdate() != 1) {
                throw new IllegalArgumentException(
                        String.format("No se pudo agregar el registro: '%s'",
                                nuevaButaca.getNumero_butaca()));
            }
        } catch (IllegalArgumentException | SQLException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
            throw new IllegalArgumentException(ex.getMessage());
        }
    }
    
    public void agregar(Butaca b) {
        agregar(String.valueOf((b.getNumero_butaca())), b);
    }
    
    @Override
    public Butaca recuperar(String numero_butaca) {
        Butaca butaca = null;
        try {
            try (Connection cnx = bd.getConnection();
                    PreparedStatement stm = cnx.prepareStatement(ButacaCRUD.RECUPERAR)) {
                stm.clearParameters();
                stm.setString(1, numero_butaca);
                try (ResultSet rs = stm.executeQuery()) {
                    if (rs.next()) {
                        butaca = new Butaca(
                                rs.getInt("numero_butaca"),
                                rs.getBoolean("estado"),
                                FuncionDAO.obtenerInstancia().recuperar(rs.getString("funcion_codigo_funcion"))
                        );
                    }
                }
            }
        } catch (SQLException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
        }
        return butaca;
    }
    
    @Override
    public void actualizar(String id, Butaca butaca) {
        try (Connection cnx = bd.getConnection();
                PreparedStatement stm = cnx.prepareStatement(ButacaCRUD.ACTUALIZAR)) {
            stm.clearParameters();
            stm.setInt(1, butaca.getNumero_butaca());
            stm.setBoolean(2, butaca.isEstado());
            stm.setInt(3, butaca.getFuncion().getCodigo());
            stm.setInt(4, butaca.getNumero_butaca());
            if (stm.executeUpdate() != 1) {
                throw new IllegalArgumentException(
                        String.format("No se pudo actualizar el registro: '%s'",
                                butaca.getNumero_butaca()));
            }
        } catch (IllegalArgumentException | SQLException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
            throw new IllegalArgumentException(ex.getMessage());
        }
    }

    public void actualizar(Butaca b) {
        actualizar(String.valueOf((b.getNumero_butaca())), b);
    }
    
    @Override
    public void eliminar(String numero_butaca) {
        try {
            try (Connection cnx = bd.getConnection();
                    PreparedStatement stm = cnx.prepareStatement(ButacaCRUD.ELIMINAR)) {
                stm.clearParameters();
                stm.setString(1, numero_butaca);
                if (stm.executeUpdate() != 1) {
                    throw new IllegalArgumentException();
                }
            }
        } catch (SQLException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
            throw new IllegalArgumentException(ex.getMessage());
        }
    }
    
    private static ButacaDAO instancia = null;
    private BaseDatos bd;
}
