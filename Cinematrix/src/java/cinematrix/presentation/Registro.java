/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinematrix.presentation;

import cinematrix.logic.Model;
import cinematrix.logic.Usuario;
import javax.annotation.security.PermitAll;
import javax.ws.rs.Consumes;
import javax.ws.rs.NotAcceptableException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

@Path("/registro")
@PermitAll
public class Registro {
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void registrar(Usuario u) {
        try {
            Model.instance().registraUsuario(u);
        } catch (Exception e) {
            throw new NotAcceptableException();
        }
    }
    
}
