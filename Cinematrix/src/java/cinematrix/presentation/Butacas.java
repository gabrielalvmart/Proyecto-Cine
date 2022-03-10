
package cinematrix.presentation;

import cinematrix.logic.Butaca;
import cinematrix.logic.Model;
import java.util.List;
import javax.annotation.security.PermitAll;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("/butacas")
@PermitAll
public class Butacas {
    
    @GET
    @Path("{codigo}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Butaca> get(@PathParam("codigo") int codigo) {
            return Model.instance().listarButacas(codigo);
    }
    
}
