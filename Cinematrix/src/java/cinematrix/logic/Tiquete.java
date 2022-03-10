/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinematrix.logic;

import java.util.List;

/**
 *
 * @author andre
 */
public class Tiquete {

    public Tiquete() {
    }

    public Tiquete(int codigo, Usuario usuario, int funcion, String butacas, String fecha, String pelicula, String sala, String tarjeta, int precio) {
        this.codigo = codigo;
        this.usuario = usuario;
        this.funcion = funcion;
        this.butacas = butacas;
        this.fecha = fecha;
        this.pelicula = pelicula;
        this.sala = sala;
        this.tarjeta = tarjeta;
        this.precio = precio;
    }
    
    public Tiquete(int funcion, String butacas, String fecha, String pelicula, String sala, String tarjeta, int precio) {
        this.codigo = 0;
        this.usuario = null;
        this.funcion = funcion;
        this.butacas = butacas;
        this.fecha = fecha;
        this.pelicula = pelicula;
        this.sala = sala;
        this.tarjeta = tarjeta;
        this.precio = precio;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public int getFuncion() {
        return funcion;
    }
    
    public void setFuncion(int funcion) {
        this.funcion = funcion;
    }
    
    public int getCodigo() {
        return codigo;
    }
    
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    
    public String getFecha() {
        return fecha;
    }
    
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
    public String getPelicula() {
        return pelicula;
    }
    
    public void setPelicula(String pelicula) {
        this.pelicula = pelicula;
    }

    public String getButacas() {
        return butacas;
    }

    public void setButacas(String butacas) {
        this.butacas = butacas;
    }
    
    public String getSala() {
        return sala;
    }

    public void setSala(String sala) {
        this.sala = sala;
    }
    
    public String getTarjeta() {
        return tarjeta;
    }

    public void setTarjeta(String tarjeta) {
        this.tarjeta = tarjeta;
    }
    
    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }
    
    private int codigo;
    private Usuario usuario;
    private String fecha;
    private String pelicula;
    private int funcion;
    private String butacas;
    private String sala;
    private String tarjeta;
    private int precio;
}