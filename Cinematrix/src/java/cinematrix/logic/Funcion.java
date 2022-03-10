/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinematrix.logic;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 *
 * @author andre
 */
public class Funcion {

    public Funcion() {
    }

    public Funcion(int codigo, String pelicula, String sala, String fecha, float precio) {
        this.codigo = codigo;
        this.pelicula = pelicula;
        this.sala = sala;
        this.fecha = fecha;
        this.precio = precio;
    }
    
    public Funcion(String pelicula, String sala, String fecha, float precio) {
        this.codigo = 0;
        this.pelicula = pelicula;
        this.sala = sala;
        this.fecha = fecha;
        this.precio = precio;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    
    public String getPelicula() {
        return pelicula;
    }

    public void setPelicula(String pelicula) {
        this.pelicula = pelicula;
    }

    public String getSala() {
        return sala;
    }

    public void setSala(String sala) {
        this.sala = sala;
    }

    public String getFecha() {
        
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }
    
    private int codigo;
    private String pelicula;
    private String sala;
    private String fecha;
    private float precio;
}
