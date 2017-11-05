package cl.duoc.dej.veterinaria.service;

import cl.duoc.dej.veterinaria.entity.Categoria;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class CategoriaService {

    static final long serialVersionUID = 12L;
    
    @PersistenceContext
    EntityManager em;    

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
    
    
}
