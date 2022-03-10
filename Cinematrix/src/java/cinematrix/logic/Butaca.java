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

    public Butaca(int numero_butaca, int estado, int funcion) {
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

    public int isEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
    
    public int getFuncion() {
        return funcion;
    }

    public void setFuncion(int funcion) {
        this.funcion = funcion;
    }

    @Override
    public String toString() {
        return String.format("%d, %d, %d", getFuncion(), isEstado(), getNumero_butaca()); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
    private int funcion;
    private int numero_butaca;
    private int estado;
}
