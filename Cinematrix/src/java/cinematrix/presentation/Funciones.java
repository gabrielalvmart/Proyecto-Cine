/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinematrix.presentation;

import cinematrix.logic.Funcion;
import cinematrix.logic.Model;
import java.util.List;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.NotAcceptableException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/funciones")
@PermitAll
public class Funciones {
    
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<Funcion> listarFunciones(@DefaultValue("") @QueryParam("codigo") String codigo) {
        return Model.instance().listarFunciones();
    }
    
    @GET
    @Path("{codigo}")
    @Produces({MediaType.APPLICATION_JSON})
    public Funcion get(@PathParam("codigo") int codigo) {
        try {
            return Model.instance().getFuncion(codigo);
        } catch (Exception ex) {
            throw new NotFoundException(); 
        }
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed({"ADM"})
    public void addFuncion(Funcion f) {
        try {
            Model.instance().agregarFuncion(f);
        } catch (Exception ex) {
            throw new NotAcceptableException();
        }
    }
}
