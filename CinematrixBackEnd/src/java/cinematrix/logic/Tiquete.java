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

    public Tiquete(int codigo_tiquete, Usuario usuario, Funcion funcion, String butacas) {
        this.codigo_tiquete = codigo_tiquete;
        this.usuario = usuario;
        this.funcion = funcion;
        this.butacas = butacas;
    }
    
    public Tiquete(Usuario usuario, Funcion funcion, String butacas) {
        this.codigo_tiquete = 0;
        this.usuario = usuario;
        this.funcion = funcion;
        this.butacas = butacas;
    }

    public int getCodigo() {
        return codigo_tiquete;
    }

    public void setCodigo(int codigo_tiquete) {
        this.codigo_tiquete = codigo_tiquete;
    }
    
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Funcion getFuncion() {
        return funcion;
    }
    
    public void setFuncion(Funcion funcion) {
        this.funcion = funcion;
    }

    public String getButacas() {
        return butacas;
    }

    public void setButacas(String butacas) {
        this.butacas = butacas;
    }
    
    private int codigo_tiquete;
    private Usuario usuario;
    private Funcion funcion;
    private String butacas;
}