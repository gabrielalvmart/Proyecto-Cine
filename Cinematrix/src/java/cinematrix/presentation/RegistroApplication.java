package cinematrix.presentation;

import cinematrix.filter.RestfulFilter;
import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import org.glassfish.jersey.media.multipart.MultiPartFeature;

@ApplicationPath("api")
public class RegistroApplication extends Application {
    @Override
    public Set<Class<?>> getClasses() {

        HashSet<Class<?>> classes = new HashSet<>();
        classes.add(MultiPartFeature.class);
        classes.add(Usuarios.class);
        classes.add(Login.class);
        classes.add(Registro.class);
        classes.add(Peliculas.class);
        classes.add(Salas.class);
        classes.add(Funciones.class);
        classes.add(Butacas.class);
        classes.add(Tiquetes.class);
        classes.add(RestfulFilter.class);
        return classes;
    }   
}
