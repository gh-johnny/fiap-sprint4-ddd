package com.dasa.keepinventory.dao;

import com.dasa.keepinventory.model.Unidade;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class UnidadeDao {

    @PersistenceContext
    private EntityManager em;

    public Unidade save(Unidade u) {
        em.persist(u);
        return u;
    }

    public List<Unidade> findAll() {
        return em.createQuery("SELECT u FROM Unidade u", Unidade.class)
                 .getResultList();
    }

    public Unidade findById(Long id) {
        return em.find(Unidade.class, id);
    }

    public void delete(Long id) {
        Unidade u = findById(id);
        if (u != null) em.remove(u);
    }
}
