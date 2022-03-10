/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinematrix.logic;

/**
 *
 * @author andre
 */
public class Pelicula {

    public Pelicula(int codigo, String nombre, String genero, float precio) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.genero = genero;
        this.precio = precio;
    }
    
    public Pelicula(String nombre, String genero, float precio) {
        this.codigo = 0;
        this.nombre = nombre;
        this.genero = genero;
        this.precio = precio;
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

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }
    
    
    private int codigo;
    private String nombre;
    private String genero;
    private float precio;
}
