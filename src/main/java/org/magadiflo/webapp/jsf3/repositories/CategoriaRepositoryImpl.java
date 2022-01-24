package org.magadiflo.webapp.jsf3.repositories;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import org.magadiflo.webapp.jsf3.entities.Categoria;

import java.util.List;

@RequestScoped
public class CategoriaRepositoryImpl implements CrudRepository<Categoria> {

    @Inject
    private EntityManager em;

    @Override
    public List<Categoria> listar() {
        return this.em.createQuery("SELECT c FROM Categoria AS c", Categoria.class).getResultList();
    }

    @Override
    public Categoria porId(Long id) {
        return this.em.find(Categoria.class, id);
    }

    @Override
    public void guardar(Categoria categoria) {
        if (categoria.getId() != null && categoria.getId() > 0) {
            this.em.merge(categoria);
        } else {
            this.em.persist(categoria);
        }
    }

    @Override
    public void eliminar(Long id) {
        Categoria c = this.porId(id);
        this.em.remove(c);
    }
}
