/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinematrix.logic;

import cinematrix.data.ButacaDAO;
import cinematrix.data.FuncionDAO;
import cinematrix.data.PeliculaDAO;
import cinematrix.data.SalaDAO;
import cinematrix.data.TiqueteDAO;
import cinematrix.data.UsuarioDAO;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author andre
 */
public class Model {

    private static Model uniqueInstance;

    public static Model instance() {
        if (uniqueInstance == null) {
            uniqueInstance = new Model();
        }
        return uniqueInstance;
    }

    private Model() {
        usuarios = cargaUsuarios();
        peliculas = cargarPeliculas();
        salas = cargarSalas();
        funciones = cargaFunciones();
        tiquetes = cargaTiquetes();
        butacas = cargaButacas();
        peliculaxfuncion();
    }

    public HashMap<String, Usuario> getUsuarios() {
        return usuarios;
    }

    public List<Pelicula> getPeliculas() {
        return peliculas;
    }

    public List<Sala> getSalas() {
        return salas;
    }

    public List<Funcion> getFunciones() {
        return funciones;
    }

    public List<Tiquete> getTiquetes() {
        return tiquetes;
    }
    
    public List<Butaca> getButacas() {
        return butacas;
    }

    
    //---------------------Usuarios------------------------------
    
    public Usuario usuarioFind(Usuario user) throws Exception {
        Usuario u = usuarios.get(user.getCedula());
        if (u==null) throw new Exception("Usuario no existe");
        return u;
    }

    public HashMap<String, Usuario> cargaUsuarios() {
        HashMap<String, Usuario> usersMap = new HashMap<String, Usuario>();
        List<Usuario> users = UsuarioDAO.obtenerInstancia().listarTodos();
        
        for (Usuario u : users) {
            usersMap.put(u.getCedula(), u);
        }
        
        return usersMap;
    }
    
    public List<Usuario> listarUsuarios() {
        return new ArrayList<>(usuarios.values());
    }

    public Usuario registraUsuario(Usuario u) throws Exception {
        if (UsuarioDAO.obtenerInstancia().recuperar(u.getCedula()) != null) {
            throw new Exception("406-persona ya existe");
        } else {
            UsuarioDAO.obtenerInstancia().agregar(u);
            usuarios = cargaUsuarios();
            return u;
        }
    }
    
    //---------------------Peliculas------------------------------
    
    public List<Pelicula> cargarPeliculas() {
        return PeliculaDAO.obtenerInstancia().listarTodos();
    }
    
    public void peliculaxfuncion(){
        for(Funcion f: funciones){
            for(Pelicula p: peliculas){
                if(f.getPelicula().equals(p.getNombre())){
                    p.agregaFuncion(f);
                    System.out.println(f.getPelicula());
                }
            }
        }
    }
    
    public List<Pelicula> listarPeliculas() {
        peliculas = cargarPeliculas();
        peliculaxfuncion();
        return peliculas;
    }
    
    public List<Pelicula> getPelicula(String nombre) {
        List<Pelicula> search = new ArrayList<>();
        for(Pelicula p: peliculas){
            if(p.getNombre().equalsIgnoreCase(nombre)){
                search.add(p);
            }
        }
        return search;
    }
    
    public Pelicula agregarPelicula(Pelicula p) throws Exception {
        if (PeliculaDAO.obtenerInstancia().recuperar(String.valueOf(p.getCodigo())) != null) {
            throw new Exception("406-pelicula ya existe");
        } else {
            PeliculaDAO.obtenerInstancia().agregar(p);
            peliculas = cargarPeliculas();
            return p;
        }
    }
    
    //---------------------Salas------------------------------
    
    public List<Sala> cargarSalas() {
        return SalaDAO.obtenerInstancia().listarTodos();
    }
    
    public List<Sala> listarSalas() {
        salas = cargarSalas();
        return salas;
    }
    
    public Sala agregarSala(Sala s) throws Exception {
        if (SalaDAO.obtenerInstancia().recuperar(s.getNumero_Sala()) != null) {
            throw new Exception("406-sala ya existe");
        } else {
            SalaDAO.obtenerInstancia().agregar(s);
            salas = cargarSalas();
            return s;
        }
    }
    
    //---------------------Funcion------------------------------
    public List<Funcion> cargaFunciones() {
        return FuncionDAO.obtenerInstancia().listarTodos();
    }
    
    public List<Funcion> listarFunciones() {
        funciones = cargaFunciones();
        return funciones;
    }
    
    public Funcion agregarFuncion(Funcion f) throws Exception {
        if (FuncionDAO.obtenerInstancia().recuperar(String.valueOf(f.getCodigo())) != null) {
            throw new Exception("406-funcion ya existe");
        } else {
            FuncionDAO.obtenerInstancia().agregar(f);
            funciones = cargaFunciones();
            butacas = cargaButacas();
            agregaButacas(f);
            
            return f;
        }
    }
    
    public Funcion getFuncion(int codigo){
        for(Funcion f: funciones){
            if(f.getCodigo() == codigo){
                return f;
            }
        }
        return null;
    }
    
    //---------------------Butaca------------------------------
    
    public List<Butaca> cargaButacas(){
        return ButacaDAO.obtenerInstancia().listarTodos();
    }
    
    public List<Butaca> listarButacas(int codigo){
        butacas = cargaButacas();
        
        List<Butaca> butacasxfuncion = new ArrayList<>();
        
        for(Butaca b: butacas){
            if(b.getFuncion() == codigo){
                butacasxfuncion.add(b);
            }
        }
          
        return butacasxfuncion;
    }
    
    public void agregaButacas(Funcion f){
        
        for(int i = 0; i < 48; i++){
            Butaca bt = new Butaca(i + 1, 0, funciones.get(funciones.size() - 1).getCodigo());
            ButacaDAO.obtenerInstancia().agregar(bt);
        }
        
    }
    
    //---------------------Tiquete------------------------------
    
    public List<Tiquete> cargaTiquetes(){
        return TiqueteDAO.obtenerInstancia().listarTodos();
    }
    
    public Tiquete agregaTiquete(Tiquete t) throws Exception{
        if (TiqueteDAO.obtenerInstancia().recuperar(String.valueOf(t.getCodigo())) != null) {
            throw new Exception("406-sala ya existe");
        } else {
            System.out.printf("%s, %s %n",t.getPelicula(), t.getButacas());
            TiqueteDAO.obtenerInstancia().agregar(t);
            String[] num_butacas = t.getButacas().split(",");
            for(String n: num_butacas){
                System.out.println(new Butaca(Integer.parseInt(n), 1, t.getFuncion()));
                ButacaDAO.obtenerInstancia().actualizar(new Butaca(Integer.parseInt(n), 1, t.getFuncion()));
            }
            butacas = cargaButacas();
            tiquetes = cargaTiquetes();
            return t;
        }
    }
    
    public List<Tiquete> getTiquetes(String nombre){
        List<Tiquete> tiquetexusuario = new ArrayList<>();
        for(Tiquete t: tiquetes){
            if(t.getUsuario().getNombre().equalsIgnoreCase(nombre)){
                tiquetexusuario.add(t);
            }
        }
        return tiquetexusuario;
    }
    
    public List<Tiquete> listarTiquetes(){
        tiquetes = cargaTiquetes();
        return tiquetes;
    }
    
    private HashMap<String, Usuario> usuarios;
    private List<Pelicula> peliculas;
    private List<Sala> salas;
    private List<Funcion> funciones;
    private List<Butaca> butacas;
    private List<Tiquete> tiquetes;
}
