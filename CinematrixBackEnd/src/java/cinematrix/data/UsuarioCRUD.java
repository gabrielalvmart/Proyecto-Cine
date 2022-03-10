/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinematrix.data;

/**
 *
 * @author andre
 */
public class UsuarioCRUD {
    
    protected static final String LISTAR
            = "select cedula, nombre, contraseña, perfil from usuario "
            + "order by cedula;";
    
    protected static final String AGREGAR
            = "insert into usuario (cedula, nombre, contraseña, perfil) "
            + "values (?, ?, ?, ?);";
    
    protected static final String RECUPERAR
            = "select cedula, nombre, contraseña, perfil from usuario "
            + "where cedula = ?;";
    
    protected static final String ACTUALIZAR
            = "update usuario set cedula = ?, nombre = ?, contraseña = ?, perfil = ? "
            + "where cedula = ?;";
    
    protected static final String ELIMINAR 
            = "delete from usuario "
            + "where cedula = ?;";
}
