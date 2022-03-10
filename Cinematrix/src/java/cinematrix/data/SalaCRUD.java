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
public class SalaCRUD {
    
    protected static final String LISTAR
            = "select numero_Sala, numero_butacas from sala "
            + "order by numero_Sala;";
    
    protected static final String AGREGAR
            = "insert into sala (numero_Sala, numero_butacas) "
            + "values (?, ?);";
    
    protected static final String RECUPERAR
            = "select numero_Sala, numero_butacas from sala "
            + "where numero_Sala = ?;";
    
    protected static final String ACTUALIZAR
            = "update sala set numero_Sala = ?, numero_butacas = ? "
            + "where numero_Sala = ?;";
    
    protected static final String ELIMINAR 
            = "delete from sala "
            + "where numero_Sala = ?;";
}
