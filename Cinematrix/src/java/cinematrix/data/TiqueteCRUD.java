/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinematrix.data;

/**
 *
 * @author andre
 */
public class TiqueteCRUD {
    
    protected static final String LISTAR
            = "select codigo_tiquete, Usuario_cedula, Funcion_codigo_funcion, butacas, fecha, pelicula, sala, numero_tarjeta, precio from tiquete "
            + "order by codigo_tiquete;";
    
    protected static final String AGREGAR
            = "insert into tiquete (Usuario_cedula, Funcion_codigo_funcion, butacas, fecha, pelicula, sala, numero_tarjeta, precio) "
            + "values (?, ?, ?, ?, ?, ?, ?, ?);";
    
    protected static final String RECUPERAR
            = "select codigo_tiquete, Usuario_cedula, Funcion_codigo_funcion, butacas, fecha, pelicula, sala, numero_tarjeta, precio from tiquete "
            + "where codigo_tiquete = ?;";
    
    protected static final String ACTUALIZAR
            = "update tiquete set codigo_tiquete = ?, Usuario_cedula = ?, Funcion_codigo_funcion = ?, butacas = ?, fecha = ?, pelicula = ?, sala = ?, numero_tarjeta = ?, precio = ? "
            + "where codigo_tiquete = ?;";
    
    protected static final String ELIMINAR 
            = "delete from tiquete "
            + "where codigo_tiquete = ?;";
    
}
