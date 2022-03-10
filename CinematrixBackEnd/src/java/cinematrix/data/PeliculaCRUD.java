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
public class PeliculaCRUD {
    
    protected static final String LISTAR
            = "select codigo, nombre, genero, precio from pelicula "
            + "order by codigo;";
    
    protected static final String AGREGAR
            = "insert into pelicula (nombre, genero, precio) "
            + "values (?, ?, ?);";
    
    protected static final String RECUPERAR
            = "select codigo, nombre, genero, precio from pelicula "
            + "where codigo = ?;";
    
    protected static final String ACTUALIZAR
            = "update pelicula set codigo = ?, nombre = ?, genero = ?, precio = ? "
            + "where codigo = ?;";
    
    protected static final String ELIMINAR 
            = "delete from pelicula "
            + "where codigo = ?;";
}
