/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinematrix.logic;

import java.time.LocalDate;

/**
 *
 * @author andre
 */
public class Funcion {

    public Funcion() {
    }

    public Funcion(int codigo, Sala sala, Pelicula pelicula, LocalDate fecha) {
        this.codigo = codigo;
        this.sala = sala;
        this.pelicula = pelicula;
        this.fecha = fecha;
    }
    
    public Funcion(Sala sala, Pelicula pelicula, LocalDate fecha) {
        this.codigo = 0;
        this.sala = sala;
        this.pelicula = pelicula;
        this.fecha = fecha;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public Pelicula getPelicula() {
        return pelicula;
    }

    public void setPelicula(Pelicula pelicula) {
        this.pelicula = pelicula;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
    
    private int codigo;
    private Sala sala;
    private Pelicula pelicula;
    private LocalDate fecha;
}
