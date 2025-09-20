package com.dasa.keepinventory.dao;

import com.dasa.keepinventory.model.Responsavel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class ResponsavelDao {

    @PersistenceContext
    private EntityManager em;

    public Responsavel save(Responsavel r) {
        em.persist(r);
        return r;
    }

    public List<Responsavel> findAll() {
        return em.createQuery("SELECT r FROM Responsavel r", Responsavel.class)
                 .getResultList();
    }

    public Responsavel findById(Long id) {
        return em.find(Responsavel.class, id);
    }

    public void delete(Long id) {
        Responsavel r = findById(id);
        if (r != null) em.remove(r);
    }
}
