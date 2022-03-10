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
public class Pelicula {

    public Pelicula(int codigo, String nombre, String genero) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.genero = genero;
        this.funciones = new ArrayList<>();
    }
    
    public Pelicula(String nombre, String genero) {
        this.codigo = 0;
        this.nombre = nombre;
        this.genero = genero;
        this.funciones = new ArrayList<>();
    }
    
    public Pelicula() {
    }
    
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }
    
    public List<Funcion> getFunciones() {
        return funciones;
    }

    public void setFunciones(List<Funcion> funciones) {
        this.funciones = funciones;
    }
    
    public void agregaFuncion(Funcion f){
        funciones.add(f);
    }
    
    private int codigo;
    private String nombre;
    private String genero;
    List<Funcion> funciones;
}
