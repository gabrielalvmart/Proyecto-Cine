package cinematrix.logic;

/**
 *
 * @author andre
 */
public class Usuario {

    public Usuario() {
    }

    public Usuario(String cedula, String nombre, String password, String perfil) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.password = password;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    
    
    private String cedula;
    private String nombre;
    private String password;
    private String perfil;
}
