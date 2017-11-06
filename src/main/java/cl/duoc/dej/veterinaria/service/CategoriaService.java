package cl.duoc.dej.veterinaria.service;

import cl.duoc.dej.veterinaria.entity.Categoria;
import cl.duoc.dej.veterinaria.entity.Producto;
import cl.duoc.dej.veterinaria.exception.CategoriaNoEncontradaException;
import cl.duoc.dej.veterinaria.exception.ProductoNoEncontradoException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class CategoriaService {

    static final long serialVersionUID = 12L;
    
    @PersistenceContext
    EntityManager em;    
    
    Logger logger = Logger.getLogger(this.getClass().getSimpleName());

    // Constructores
    public CategoriaService() {
    }

    // métodos
    public Categoria crearCategoria(Categoria categoria) {
        em.persist(categoria);
        return categoria;
    }
    
    public Categoria getCategoriaById(Long categoriaId) {
        Categoria categoria = em.find(Categoria.class, categoriaId);
        return categoria;
    }
    
    public List<Categoria> getCategorias() {
        TypedQuery<Categoria> query = em.createQuery("SELECT c FROM Categoria c", Categoria.class);
        return query.getResultList();
    }
    
    public void eliminarCategoria(Long categoriaId) throws CategoriaNoEncontradaException {
        Categoria c = getCategoriaById(categoriaId);
        if (c == null) {
            String mensajeException = String.format("Categoria con ID %s no encontrada para ser eliminada", categoriaId);
            logger.log(Level.SEVERE, mensajeException);
            throw new CategoriaNoEncontradaException(mensajeException);
        }
        em.remove(c);
    }
}
