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
public class Sala {

    public Sala() {
    }

    public Sala(String numero_Sala, int numero_butacas) {
        this.numero_Sala = numero_Sala;
        this.numero_butacas = numero_butacas;
    }

    public String getNumero_Sala() {
        return numero_Sala;
    }

    public void setNumero_Sala(String numero_Sala) {
        this.numero_Sala = numero_Sala;
    }

    public int getNumero_butacas() {
        return numero_butacas;
    }

    public void setNumero_butacas(int numero_butacas) {
        this.numero_butacas = numero_butacas;
    }
    
    private String numero_Sala;
    private int numero_butacas;
}
