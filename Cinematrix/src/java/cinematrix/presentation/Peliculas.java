/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinematrix.presentation;

import cinematrix.logic.Model;
import cinematrix.logic.Pelicula;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.NotAcceptableException;
import javax.ws.rs.POST;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import org.glassfish.jersey.media.multipart.FormDataParam;

@Path("/peliculas")
@PermitAll
public class Peliculas {

    String location = "C:/Users/andre/Desktop/imagenes/";

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<Pelicula> listarPeliculas(@DefaultValue("") @QueryParam("nombre") String nombre) {
        if (!nombre.isEmpty()) {
            return Model.instance().getPelicula(nombre);
        }
        return Model.instance().listarPeliculas();
    }

    @GET
    @Path("{nombre}/imagen")
    @Produces("image/png")
    public Response getImage(@PathParam("nombre") String nombre) throws IOException {
        File file = new File(location + nombre);
        ResponseBuilder response = Response.ok((Object) file);
        return response.build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed({"ADM"})
    public void addPelicula(Pelicula p) {
        try {
            Model.instance().agregarPelicula(p);
        } catch (Exception ex) {
            throw new NotAcceptableException();
        }
    }

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Path("{nombre}/imagen")
    @RolesAllowed({"ADM"})
    public void addImage(@PathParam("nombre") String nombre, @FormDataParam("imagen") InputStream imagenStream) {
        try {
            int read = 0;
            byte[] bytes = new byte[1024];

            OutputStream out = new FileOutputStream(new File(location + nombre));
            while ((read = imagenStream.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            out.flush();
            out.close();
        } catch (Exception ex) {
            throw new NotAcceptableException();
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed({"ADM"})
    public void update(Pelicula p) {
        try {
            //Model.instance().personaUpdate(p);
        } catch (Exception ex) {
            throw new NotFoundException();
        }
    }

    @DELETE
    @Path("{codigo}")
    @RolesAllowed({"ADM"})
    public void delete(@PathParam("codigo") int cedula) {
        try {
            //Model.instance().personaDelete(cedula);
        } catch (Exception ex) {
            throw new NotFoundException();
        }
    }
}
