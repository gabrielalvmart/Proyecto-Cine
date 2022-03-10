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
public class Usuario {

    public Usuario() {
    }

    public Usuario(String cedula, String nombre, String contraseña, int perfil) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.contraseña = contraseña;
        this.perfil = perfil;
    }
    
    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
    
    public int getPerfil() {
        return perfil;
    }

    public void setPerfil(int perfil) {
        this.perfil = perfil;
    }

    
    
    private String cedula;
    private String nombre;
    private String contraseña;
    private int perfil;
}
