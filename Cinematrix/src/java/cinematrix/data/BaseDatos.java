/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinematrix.data;

import com.mysql.cj.jdbc.MysqlDataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * @author andre
 */
public class BaseDatos extends MysqlDataSource {
    private BaseDatos() throws IOException {
        this.configuration = new Properties();
        configuration.load(getClass().getResourceAsStream(CONFIGURATION_PATH));

        String URL_conexion = "jdbc:mysql://" + "localhost" + ":" + "3306" + "/"
                + "cinematrix" + "?user=" + "root" + "&password=" + "root" + "&serverTimezone=UTC&autoReconnect=true&useSSL=false";

        setURL(URL_conexion);
        setUser(this.configuration.getProperty("user"));
        setPassword(this.configuration.getProperty("password"));
    }

    @Override
    public Connection getConnection() throws SQLException {
        return super.getConnection();
    }

    public static BaseDatos obtenerInstancia() throws IOException {
        if (instancia == null) {
            try {
                instancia = new BaseDatos();
            } catch (IOException ex) {
                System.err.printf("Excepción: '%s'%n", ex.getMessage());
                throw ex;
            }
        }
        return instancia;
    }

    private static final String CONFIGURATION_PATH = "db.properties";
    private static BaseDatos instancia = null;
    private Properties configuration = null;
}
