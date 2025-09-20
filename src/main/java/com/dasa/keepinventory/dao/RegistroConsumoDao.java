package com.dasa.keepinventory.dao;

import com.dasa.keepinventory.model.RegistroConsumo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class RegistroConsumoDao {

    @PersistenceContext
    private EntityManager em;

    public RegistroConsumo save(RegistroConsumo r) {
        em.persist(r);
        return r;
    }

    public List<RegistroConsumo> findAll() {
        return em.createQuery("SELECT r FROM RegistroConsumo r", RegistroConsumo.class)
                 .getResultList();
    }

    public RegistroConsumo findById(Long id) {
        return em.find(RegistroConsumo.class, id);
    }

    public void delete(Long id) {
        RegistroConsumo r = findById(id);
        if (r != null) em.remove(r);
    }
}
