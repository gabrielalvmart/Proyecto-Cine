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
            = "select f.codigo_funcion, p.nombre, f.Sala_numero_Sala, f.fecha, f.precio from funcion f, pelicula p "
            + "where f.Pelicula_codigo = p.codigo "
            + "order by f.codigo_funcion;";
    
    protected static final String AGREGAR
            = "insert into funcion (Pelicula_codigo, Sala_numero_Sala, fecha, precio) "
            + "values (?, ?, ?, ?);";
    
    protected static final String RECUPERAR
            = "select f.codigo_funcion, p.nombre, f.Sala_numero_Sala, f.fecha, f.precio from funcion f, pelicula p "
            + "where f.Pelicula_codigo = p.codigo "
            + "and f.codigo_funcion = ?;";
    
    protected static final String ACTUALIZAR
            = "update funcion set codigo_funcion = ?, Pelicula_codigo = ?, Sala_numero_Sala = ?, fecha = ?, precio = ? "
            + "where codigo_funcion = ?;";
    
    protected static final String ELIMINAR 
            = "delete from funcion "
            + "where codigo_funcion = ?;";
}
