
package cinematrix.presentation;

import cinematrix.logic.Model;
import cinematrix.logic.Tiquete;
import cinematrix.logic.Usuario;
import java.util.List;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.NotAcceptableException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

@Path("/tiquetes")
@PermitAll
public class Tiquetes {
    
    @Context
    HttpServletRequest request;
    
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<Tiquete> listaUsuarios(@DefaultValue("") @QueryParam("nombre") String nombre) {
        if (!nombre.isEmpty()) {
            return Model.instance().getTiquetes(nombre);
        }
        
        return Model.instance().getTiquetes();
    }
    
    
    @GET
    @Path("usuario")
    @Produces({MediaType.APPLICATION_JSON})
    public Usuario getUsuario(@DefaultValue("") @QueryParam("codigo") String codigo) {
        return (Usuario) request.getSession(true).getAttribute("user");
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void addTiquete(Tiquete t) {
        Usuario us = (Usuario) request.getSession(true).getAttribute("user");
        t.setUsuario(us);
        try {
            Model.instance().agregaTiquete(t);
        } catch (Exception ex) {
            throw new NotAcceptableException();
        }
    }
    
}
