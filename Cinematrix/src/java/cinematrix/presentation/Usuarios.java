/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinematrix.presentation;

import cinematrix.logic.Model;
import cinematrix.logic.Usuario;
import java.util.List;
import javax.annotation.security.PermitAll;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Marvin Aguilar
 */
@Path("/usuarios")
@PermitAll
public class Usuarios {

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<Usuario> listarUsuarios(@DefaultValue("") @QueryParam("nombre") String nombre) {
        return Model.instance().listarUsuarios();
    }
    
}
