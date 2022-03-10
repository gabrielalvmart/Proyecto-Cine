/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinematrix.logic;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author andre
 */
public class Model {

    public Model() {
        usuarios = new ArrayList<>();
        peliculas = new ArrayList<>();
        salas = new ArrayList<>();
        funciones = new ArrayList<>();
        tiquetes = new ArrayList<>();
    }    
    
    public List<Usuario> getUsuarios(){
        return usuarios;
    }
    
    public List<Pelicula> getPeliculas(){
        return peliculas;
    }
    
    public List<Sala> getSalas(){
        return salas;
    }
    
    public List<Funcion> getFunciones(){
        return funciones;
    }
    
    public List<Tiquete> getTiquetes(){
        return tiquetes;
    }
    
    private List<Usuario> usuarios;
    private List<Pelicula> peliculas;
    private List<Sala> salas;
    private List<Funcion> funciones;
    private List<Tiquete> tiquetes;
}
