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
public class Butaca {

    public Butaca() {
    }

    public Butaca(int numero_butaca, boolean estado, Funcion funcion) {
        this.funcion = funcion;
        this.numero_butaca = numero_butaca;
        this.estado = estado;
    }

    public int getNumero_butaca() {
        return numero_butaca;
    }

    public void setNumero_butaca(int numero_butaca) {
        this.numero_butaca = numero_butaca;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    
    public Funcion getFuncion() {
        return funcion;
    }

    public void setFuncion(Funcion funcion) {
        this.funcion = funcion;
    }
    
    private Funcion funcion;
    private int numero_butaca;
    private boolean estado;
}
