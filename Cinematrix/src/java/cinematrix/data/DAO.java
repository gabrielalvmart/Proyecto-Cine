package cinematrix.data;

import java.util.List;

/**
 *
 * @author andre
 */
public interface DAO<K, V> {

    public List<V> listarTodos();
    
    public void agregar(K id, V valor);

    public V recuperar(K id);

    public void actualizar(K id, V valor);
    
    public void eliminar(K id);


}
