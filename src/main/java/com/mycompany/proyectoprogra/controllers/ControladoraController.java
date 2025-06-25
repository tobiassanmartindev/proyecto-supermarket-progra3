
package com.mycompany.proyectoprogra.controllers;

import com.mycompany.proyectoprogra.controllers.exceptions.IllegalOrphanException;
import com.mycompany.proyectoprogra.controllers.exceptions.NonexistentEntityException;
import com.mycompany.proyectoprogra.entitys.Categorias;
import com.mycompany.proyectoprogra.entitys.Clientes;
import com.mycompany.proyectoprogra.entitys.Detalleordenes;
import com.mycompany.proyectoprogra.entitys.DetalleordenesPK;
import com.mycompany.proyectoprogra.entitys.Envios;
import com.mycompany.proyectoprogra.entitys.Ordenes;
import com.mycompany.proyectoprogra.entitys.Productos;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ControladoraController {
    
    //FUNCIONES PARA CLIENTES
    ClientesJpaController ClientePao = new ClientesJpaController();
    ProductosJpaController productoPao = new ProductosJpaController();
    CategoriasJpaController categoriaPao = new CategoriasJpaController();
    DetalleordenesJpaController  detallesPao = new DetalleordenesJpaController();
    OrdenesJpaController ordenesPao = new OrdenesJpaController();
    EnviosJpaController enviosPao = new EnviosJpaController();
    
    
    
    //CLIENTES
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
        
        Clientes cliente = ClientePao.findClienteConOrdenes(idCliente); // Utiliza el findClientes de tu ClientesJpaController
       
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
    
    public void modificarProducto(long idProducto, String nuevoNombre,double precioUnitario, Categorias nuevaCategoria) throws NonexistentEntityException, Exception{
        Productos produc = productoPao.findProductos(idProducto);
        if(produc == null){
            throw new NonexistentEntityException("El producto con ID " + idProducto + " no existe para modificar.");
        }
        produc.setNombreProducto(nuevoNombre);
        produc.setIdCategoria(nuevaCategoria);
        produc.setPrecioUnitario(precioUnitario);
        productoPao.edit(produc);
    }
    
    //Categorias
    public List<Categorias> traerCategorias() {
     return categoriaPao.findCategoriasEntities(); // O el método que devuelva todas las categorías
}

    //ENVIOS
    public void agregarEnvio(String modoEnvio, String ciudad, String estado, int codigoPostal){
        try {
            Envios nuevoEnvio = new Envios();
            nuevoEnvio.setModoEnvio(modoEnvio);
            nuevoEnvio.setCiudad(ciudad);
            nuevoEnvio.setEstado(estado);
            nuevoEnvio.setCodigoPostal(codigoPostal);
            enviosPao.create(nuevoEnvio);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Envios traerEnvio(long idEnvio){
        return enviosPao.findEnvios(idEnvio);
    }
    
    public void editarEnvio(Envios envio){
        try {
            enviosPao.edit(envio);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladoraController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
  
    
    //ORDEN
    // --- Método CLAVE para guardar una Orden completa ---
    public boolean guardarOrdenCompleta(Date fechaOrden, long idCliente, String modoEnvio, String ciudad, String estado, int codigoPostal, List<Detalleordenes> detallesTemp) {
        try {
            // 1. Validar Cliente
            Clientes cliente = ClientePao.findClientes(idCliente);
            if (cliente == null) {
                System.out.println("Error: Cliente con ID " + idCliente + " no encontrado.");
                return false;
            }

            // 2. Crear y persistir el Envío
            Envios nuevoEnvio = new Envios();
            nuevoEnvio.setModoEnvio(modoEnvio);
            nuevoEnvio.setCiudad(ciudad);
            nuevoEnvio.setEstado(estado);
            nuevoEnvio.setCodigoPostal(codigoPostal);
            enviosPao.create(nuevoEnvio); // Persiste el envío para que tenga un ID

            // 3. Crear y persistir la Orden
            Ordenes nuevaOrden = new Ordenes();
            nuevaOrden.setFechaOrden(fechaOrden);
            nuevaOrden.setIdCliente(cliente); // Asigna el objeto Cliente
            nuevaOrden.setIdEnvio(nuevoEnvio); // Asigna el objeto Envios
            
            // Inicializa la colección de detalles si no está ya inicializada por el constructor de Ordenes
            if (nuevaOrden.getDetalleordenesCollection() == null) {
                nuevaOrden.setDetalleordenesCollection(new java.util.ArrayList<>());
            }

            ordenesPao.create(nuevaOrden); // Persiste la orden para que tenga su ID

            // 4. Persistir los Detalleordenes (asociados a la nueva Orden y Productos)
            for (Detalleordenes detalle : detallesTemp) {
                // Es crucial establecer la PK compuesta y las referencias a las entidades
                DetalleordenesPK pk = new DetalleordenesPK(nuevaOrden.getIdOrden(), detalle.getProductos().getIdProducto());
                detalle.setDetalleordenesPK(pk);
                detalle.setOrdenes(nuevaOrden); // Asigna la orden recién creada
                
                // Asegúrate de que totalVenta, descuento, ganancia estén calculados antes de guardar
                // Por ejemplo, si Productos tuviera un precio:
                // float precioProducto = detalle.getProductos().getPrecio(); // Necesitas agregar 'precio' a Productos
                // detalle.setTotalVenta(precioProducto * detalle.getCantidad());
                // detalle.setDescuento(0.0f); // Asumiendo 0 por ahora, puedes agregar lógica
                // detalle.setGanancia(detalle.getTotalVenta() * 0.2f); // Ejemplo de ganancia

                detallesPao.create(detalle);
                nuevaOrden.getDetalleordenesCollection().add(detalle); // Agrega a la colección de la orden
            }
            
            // Opcional: Si quieres que la colección de detalles se actualice en la entidad Ordenes,
            // puedes hacer un 'edit' de la orden después de añadir todos los detalles.
            // ordenesC.edit(nuevaOrden);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            // Podrías loggear el error o mostrar un mensaje más específico
            return false;
        }
    }
    
     public Ordenes traerOrden(long idOrden) {
        return ordenesPao.findOrdenes(idOrden);
    }
     
     public List<Ordenes> traerTodasLasOrdenes() {
        return ordenesPao.findOrdenesEntities();
    }
    

}
