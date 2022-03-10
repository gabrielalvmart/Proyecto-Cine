/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinematrix.data;

import cinematrix.logic.Funcion;
import cinematrix.logic.Pelicula;
import cinematrix.logic.Sala;
import cinematrix.logic.Tiquete;
import cinematrix.logic.Usuario;


/**
 *
 * @author andre
 */
public class main {

    public static void main(String args[]) {
        
        Pelicula p1 = PeliculaDAO.obtenerInstancia().recuperar("1");
        Pelicula p2 = PeliculaDAO.obtenerInstancia().recuperar("1");
        Sala s1 = SalaDAO.obtenerInstancia().recuperar("A5");
        Sala s2 = SalaDAO.obtenerInstancia().recuperar("A6");
        Funcion f1 = FuncionDAO.obtenerInstancia().recuperar("1000");
        Funcion f2 = FuncionDAO.obtenerInstancia().recuperar("1001");
        Usuario u1 = UsuarioDAO.obtenerInstancia().recuperar("117520958");
        Usuario u2 = UsuarioDAO.obtenerInstancia().recuperar("116830152");
        
        TiqueteDAO.obtenerInstancia().agregar(new Tiquete(u1, f1, "1, 2, 3"));
        TiqueteDAO.obtenerInstancia().agregar(new Tiquete(u2, f1, "4"));
        TiqueteDAO.obtenerInstancia().agregar(new Tiquete(u1, f2, "1, 2, 3"));
        TiqueteDAO.obtenerInstancia().agregar(new Tiquete(u2, f2, "4"));
    }
    
    
}
