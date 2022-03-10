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
public class ButacaCRUD {
    protected static final String LISTAR
            = "select numero_butaca, estado, funcion_codigo_funcion from butaca "
            + "order by numero_butaca and funcion_codigo_funcion;";
    
    protected static final String AGREGAR
            = "insert into butaca (numero_butaca, estado, funcion_codigo_funcion) "
            + "values (?, ?, ?);";
    
    protected static final String RECUPERAR
            = "select numero_butaca, estado, funcion_codigo_funcion from butaca "
            + "where numero_butaca = ?;";
    
    protected static final String ACTUALIZAR
            = "update butaca set estado = ? "
            + "where numero_butaca = ? and funcion_codigo_funcion = ?;";
    
    protected static final String ELIMINAR 
            = "delete from butaca "
            + "where numero_butaca = ?;";
}
