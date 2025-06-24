/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectoprogra.controllers;

import com.mycompany.proyectoprogra.controllers.exceptions.IllegalOrphanException;
import com.mycompany.proyectoprogra.controllers.exceptions.NonexistentEntityException;
import com.mycompany.proyectoprogra.controllers.exceptions.PreexistingEntityException;
import java.io.Serializable;
import jakarta.persistence.Query;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import com.mycompany.proyectoprogra.entitys.Categorias;
import com.mycompany.proyectoprogra.entitys.Detalleordenes;
import com.mycompany.proyectoprogra.entitys.Productos;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author totol
 */
public class ProductosJpaController implements Serializable {

    public ProductosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    public ProductosJpaController() {
        emf = Persistence.createEntityManagerFactory("persistencia");
    }
    
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Productos productos) throws PreexistingEntityException, Exception {
        if (productos.getDetalleordenesCollection() == null) {
            productos.setDetalleordenesCollection(new ArrayList<Detalleordenes>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Categorias idCategoria = productos.getIdCategoria();
            if (idCategoria != null) {
                idCategoria = em.getReference(idCategoria.getClass(), idCategoria.getIdCategoria());
                productos.setIdCategoria(idCategoria);
            }
            Collection<Detalleordenes> attachedDetalleordenesCollection = new ArrayList<Detalleordenes>();
            for (Detalleordenes detalleordenesCollectionDetalleordenesToAttach : productos.getDetalleordenesCollection()) {
                detalleordenesCollectionDetalleordenesToAttach = em.getReference(detalleordenesCollectionDetalleordenesToAttach.getClass(), detalleordenesCollectionDetalleordenesToAttach.getDetalleordenesPK());
                attachedDetalleordenesCollection.add(detalleordenesCollectionDetalleordenesToAttach);
            }
            productos.setDetalleordenesCollection(attachedDetalleordenesCollection);
            em.persist(productos);
            if (idCategoria != null) {
                idCategoria.getProductosCollection().add(productos);
                idCategoria = em.merge(idCategoria);
            }
            for (Detalleordenes detalleordenesCollectionDetalleordenes : productos.getDetalleordenesCollection()) {
                Productos oldProductosOfDetalleordenesCollectionDetalleordenes = detalleordenesCollectionDetalleordenes.getProductos();
                detalleordenesCollectionDetalleordenes.setProductos(productos);
                detalleordenesCollectionDetalleordenes = em.merge(detalleordenesCollectionDetalleordenes);
                if (oldProductosOfDetalleordenesCollectionDetalleordenes != null) {
                    oldProductosOfDetalleordenesCollectionDetalleordenes.getDetalleordenesCollection().remove(detalleordenesCollectionDetalleordenes);
                    oldProductosOfDetalleordenesCollectionDetalleordenes = em.merge(oldProductosOfDetalleordenesCollectionDetalleordenes);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findProductos(productos.getIdProducto()) != null) {
                throw new PreexistingEntityException("Productos " + productos + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Productos productos) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Productos persistentProductos = em.find(Productos.class, productos.getIdProducto());
            Categorias idCategoriaOld = persistentProductos.getIdCategoria();
            Categorias idCategoriaNew = productos.getIdCategoria();
            Collection<Detalleordenes> detalleordenesCollectionOld = persistentProductos.getDetalleordenesCollection();
            Collection<Detalleordenes> detalleordenesCollectionNew = productos.getDetalleordenesCollection();
            List<String> illegalOrphanMessages = null;
            for (Detalleordenes detalleordenesCollectionOldDetalleordenes : detalleordenesCollectionOld) {
                if (!detalleordenesCollectionNew.contains(detalleordenesCollectionOldDetalleordenes)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Detalleordenes " + detalleordenesCollectionOldDetalleordenes + " since its productos field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idCategoriaNew != null) {
                idCategoriaNew = em.getReference(idCategoriaNew.getClass(), idCategoriaNew.getIdCategoria());
                productos.setIdCategoria(idCategoriaNew);
            }
            Collection<Detalleordenes> attachedDetalleordenesCollectionNew = new ArrayList<Detalleordenes>();
            for (Detalleordenes detalleordenesCollectionNewDetalleordenesToAttach : detalleordenesCollectionNew) {
                detalleordenesCollectionNewDetalleordenesToAttach = em.getReference(detalleordenesCollectionNewDetalleordenesToAttach.getClass(), detalleordenesCollectionNewDetalleordenesToAttach.getDetalleordenesPK());
                attachedDetalleordenesCollectionNew.add(detalleordenesCollectionNewDetalleordenesToAttach);
            }
            detalleordenesCollectionNew = attachedDetalleordenesCollectionNew;
            productos.setDetalleordenesCollection(detalleordenesCollectionNew);
            productos = em.merge(productos);
            if (idCategoriaOld != null && !idCategoriaOld.equals(idCategoriaNew)) {
                idCategoriaOld.getProductosCollection().remove(productos);
                idCategoriaOld = em.merge(idCategoriaOld);
            }
            if (idCategoriaNew != null && !idCategoriaNew.equals(idCategoriaOld)) {
                idCategoriaNew.getProductosCollection().add(productos);
                idCategoriaNew = em.merge(idCategoriaNew);
            }
            for (Detalleordenes detalleordenesCollectionNewDetalleordenes : detalleordenesCollectionNew) {
                if (!detalleordenesCollectionOld.contains(detalleordenesCollectionNewDetalleordenes)) {
                    Productos oldProductosOfDetalleordenesCollectionNewDetalleordenes = detalleordenesCollectionNewDetalleordenes.getProductos();
                    detalleordenesCollectionNewDetalleordenes.setProductos(productos);
                    detalleordenesCollectionNewDetalleordenes = em.merge(detalleordenesCollectionNewDetalleordenes);
                    if (oldProductosOfDetalleordenesCollectionNewDetalleordenes != null && !oldProductosOfDetalleordenesCollectionNewDetalleordenes.equals(productos)) {
                        oldProductosOfDetalleordenesCollectionNewDetalleordenes.getDetalleordenesCollection().remove(detalleordenesCollectionNewDetalleordenes);
                        oldProductosOfDetalleordenesCollectionNewDetalleordenes = em.merge(oldProductosOfDetalleordenesCollectionNewDetalleordenes);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                long id = productos.getIdProducto();
                if (findProductos(id) == null) {
                    throw new NonexistentEntityException("The productos with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(long id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Productos productos;
            try {
                productos = em.getReference(Productos.class, id);
                productos.getIdProducto();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The productos with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Detalleordenes> detalleordenesCollectionOrphanCheck = productos.getDetalleordenesCollection();
            for (Detalleordenes detalleordenesCollectionOrphanCheckDetalleordenes : detalleordenesCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Productos (" + productos + ") cannot be destroyed since the Detalleordenes " + detalleordenesCollectionOrphanCheckDetalleordenes + " in its detalleordenesCollection field has a non-nullable productos field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Categorias idCategoria = productos.getIdCategoria();
            if (idCategoria != null) {
                idCategoria.getProductosCollection().remove(productos);
                idCategoria = em.merge(idCategoria);
            }
            em.remove(productos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Productos> findProductosEntities() {
        return findProductosEntities(true, -1, -1);
    }

    public List<Productos> findProductosEntities(int maxResults, int firstResult) {
        return findProductosEntities(false, maxResults, firstResult);
    }

    /*
    private List<Productos> findProductosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Productos.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }
    */
    private List<Productos> findProductosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        List<Productos> productos = null; // Declarar la lista aquí
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Productos.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            
            // Obtener la lista de productos
            productos = q.getResultList();

            // Forzamos la carga de la relación de Categorias para cada Producto, esto se hace mientras el em esta activo y antes del finally que cierra el entity manager 
            for (Productos p : productos) {
                if (p.getIdCategoria() != null) {
                    // Acceder a cualquier método de la categoría para forzar su inicialización.
                    p.getIdCategoria().getCategoria();
                }
            }
            return productos; // Devolver la lista que ahora tiene las categorías inicializadas
        } catch (Exception e) {
            // Manejo de la excepción, puedes loguearla
            e.printStackTrace();
            throw e; // O relanzar, o devolver null, según tu política de errores
        } finally {
            if (em != null) { // Asegurarse de que em no sea null antes de cerrarlo
                em.close();
            }
        }
    }
    
    /*
    public Productos findProductos(long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Productos.class, id);
        } finally {
            em.close();
        }
    }
    */
    
    public Productos findProductos(long id) {
        EntityManager em = getEntityManager();
        Productos producto = null; // Declara la variable aquí
        try {
            producto = em.find(Productos.class, id);
            
            // Verifica que el producto no sea nulo y que la colección exista antes de acceder a ella
            if (producto != null && producto.getDetalleordenesCollection() != null) {
                // Accede a la colección para forzar su inicialización.
                producto.getDetalleordenesCollection().size();
            }
            // (Opcional, pero recomendable si el error de Categorias vuelve a aparecer
            // o si necesitas la categoría cuando cargas un solo producto)
            if (producto != null && producto.getIdCategoria() != null) {
                producto.getIdCategoria().getCategoria();
            }
            return producto; // Devuelve el producto con la colección ya cargada
        } catch (Exception e) { // Capturar cualquier excepción
            e.printStackTrace(); // Imprime la traza para depuración
            throw e; // Relanza la excepción si es necesario que se maneje más arriba
        } finally {
            if (em != null) { // Asegúrate de que em no sea null antes de cerrar
                em.close(); // Cierra el EntityManager
            }
        }
    }
    
    public int getProductosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Productos> rt = cq.from(Productos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
