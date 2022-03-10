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
public class FuncionCRUD {
    protected static final String LISTAR
            = "select codigo_funcion, fecha, Pelicula_codigo, Sala_numero_Sala from funcion "
            + "order by funcion;";
    
    protected static final String AGREGAR
            = "insert into funcion (fecha, Pelicula_codigo, Sala_numero_Sala) "
            + "values (?, ?, ?);";
    
    protected static final String RECUPERAR
            = "select codigo_funcion, fecha, Pelicula_codigo, Sala_numero_Sala from funcion "
            + "where codigo_funcion = ?;";
    
    protected static final String ACTUALIZAR
            = "update funcion set codigo_funcion = ?, fecha = ?, Pelicula_codigo = ?, Sala_numero_Sala = ? "
            + "where codigo_funcion = ?;";
    
    protected static final String ELIMINAR 
            = "delete from funcion "
            + "where codigo_funcion = ?;";
}
