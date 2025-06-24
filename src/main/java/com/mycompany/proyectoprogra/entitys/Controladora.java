
package com.mycompany.proyectoprogra.entitys;

import com.mycompany.proyectoprogra.controllers.ControladoraController;
import com.mycompany.proyectoprogra.controllers.exceptions.IllegalOrphanException;
import com.mycompany.proyectoprogra.controllers.exceptions.NonexistentEntityException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Controladora {
    
ControladoraController controladoraPao = new ControladoraController();




//CLIENTES    
public List<Clientes> traerClientes(){
    return controladoraPao.traerClientes();
}

public Clientes traerCliente(long id_cliente){
    return controladoraPao.traerCliente(id_cliente);
}

public void agregarCliente(String nombre_cliente) throws Exception{
    Clientes cliente = new Clientes();
    cliente.setNombreCliente(nombre_cliente);
    controladoraPao.agregarCliente(cliente);
}

public void eliminarCliente(long id){
    try {
        controladoraPao.borrarCliente(id);
    } catch (IllegalOrphanException ex) {
        Logger.getLogger(Controladora.class.getName()).log(Level.SEVERE, null, ex);
    }
}

public void modificarCliente(long id, String nuevoNombre) throws Exception { // IMPORTANTE: Agrega 'throws Exception'
        try {
            controladoraPao.modificarCliente(id, nuevoNombre); // Llama al método de ControladoraController
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(Controladora.class.getName()).log(Level.SEVERE, "Cliente no encontrado para modificar (desde Controladora)", ex);
            throw ex; // Relanza la excepción para que la IGU pueda manejarla
        } catch (Exception ex) {
            Logger.getLogger(Controladora.class.getName()).log(Level.SEVERE, "Error inesperado al modificar cliente (desde Controladora)", ex);
            throw ex; // Relanza cualquier otra excepción
        }
}

// PRODUCTOS

public void agregarProducto(String nombreProducto, Categorias categoria) throws Exception {
    Productos producto = new Productos();
    producto.setNombreProducto(nombreProducto);
    producto.setIdCategoria(categoria);
    controladoraPao.agregarProducto(producto);
}

public void eliminarProducto(long idProducto) {
    try {
        controladoraPao.borrarProducto(idProducto);
    } catch (Exception ex) {
        Logger.getLogger(Controladora.class.getName()).log(Level.SEVERE, null, ex);
    }
}

public void modificarProducto(long idProducto, String nuevoNombre, Categorias nuevaCategoria) throws Exception {
    controladoraPao.modificarProducto(idProducto, nuevoNombre, nuevaCategoria);
}

public List<Productos> traerProductos() {
    return controladoraPao.traerProductos();
}

public Productos traerProducto(long idProducto) {
    return controladoraPao.traerProducto(idProducto);
}

//Categorias

public List<Categorias> traerCategorias() {
    return controladoraPao.traerCategorias();
}
    
  
    
    
}
