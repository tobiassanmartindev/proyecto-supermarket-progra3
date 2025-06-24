
package com.mycompany.proyectoprogra.controllers;

import com.mycompany.proyectoprogra.controllers.exceptions.IllegalOrphanException;
import com.mycompany.proyectoprogra.controllers.exceptions.NonexistentEntityException;
import com.mycompany.proyectoprogra.entitys.Categorias;
import com.mycompany.proyectoprogra.entitys.Clientes;
import com.mycompany.proyectoprogra.entitys.Productos;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ControladoraController {
    
    //FUNCIONES PARA CLIENTES
    ClientesJpaController ClientePao = new ClientesJpaController();
    ProductosJpaController productoPao = new ProductosJpaController();
    CategoriasJpaController categoriaPao = new CategoriasJpaController();
    
    public List<Clientes> traerClientes(){
        return ClientePao.findClientesEntities();
    }
    
    public Clientes traerCliente(long id_cliente){
        return ClientePao.findClientes(id_cliente);
    }    
    
    public void agregarCliente(Clientes cliente) throws Exception{
        ClientePao.create(cliente);
    }
    
    public void borrarCliente(long idCliente) throws IllegalOrphanException{
        try{
            ClientePao.destroy(idCliente);
        }catch(NonexistentEntityException ex){
            System.out.println("Ha ocurrido un error al eliminar el cliente: " + ex.getMessage());
        }
    }
    
    public void modificarCliente(long idCliente, String nuevoNombre) throws NonexistentEntityException, Exception {
        // 1. Busca el cliente usando el ClientesJpaController
        // Es fundamental que el objeto Clientes que modificas sea obtenido por el mismo JPA Controller.
        Clientes cliente = ClientePao.findClienteConOrdenes(idCliente); // Utiliza el findClientes de tu ClientesJpaController
        // 2. Verifica si el cliente existe
        if (cliente == null) {
            throw new NonexistentEntityException("El cliente con ID " + idCliente + " no existe para modificar.");
        }
        cliente.setNombreCliente(nuevoNombre);
        ClientePao.edit(cliente);
    }
    
    //PRODUCTOS
    
    public List<Productos> traerProductos(){
        return productoPao.findProductosEntities();
    }
    
    public Productos traerProducto(long idProducto){
        return productoPao.findProductos(idProducto);
    }   
    
    
    
    
    
    
    public void agregarProducto(Productos producto) throws Exception{
        productoPao.create(producto);
    }
    
    public void borrarProducto(long idProducto){
        try {
            productoPao.destroy(idProducto);
        } catch (IllegalOrphanException ex) {
            Logger.getLogger(ControladoraController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladoraController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void modificarProducto(long idProducto, String nuevoNombre, Categorias nuevaCategoria) throws NonexistentEntityException, Exception{
        Productos produc = productoPao.findProductos(idProducto);
        if(produc == null){
            throw new NonexistentEntityException("El producto con ID " + idProducto + " no existe para modificar.");
        }
        produc.setNombreProducto(nuevoNombre);
        produc.setIdCategoria(nuevaCategoria);
        productoPao.edit(produc);
    }
    
    //Categorias
    public List<Categorias> traerCategorias() {
     return categoriaPao.findCategoriasEntities(); // O el método que devuelva todas las categorías
}
    
    

}
