/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinematrix.data;

import cinematrix.logic.Butaca;


/**
 *
 * @author andre
 */
public class main {

    public static void main(String args[]) {
        
        
        ButacaDAO.obtenerInstancia().actualizar(new Butaca(1, 1, 1001));
    }
    
    
}
