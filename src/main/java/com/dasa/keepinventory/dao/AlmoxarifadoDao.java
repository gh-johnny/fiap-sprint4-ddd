package com.dasa.keepinventory.dao;

import com.dasa.keepinventory.model.Almoxarifado;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class AlmoxarifadoDao {

    @PersistenceContext
    private EntityManager em;

    public Almoxarifado save(Almoxarifado a) {
        em.persist(a);
        return a;
    }

    public List<Almoxarifado> findAll() {
        return em.createQuery("SELECT a FROM Almoxarifado a", Almoxarifado.class)
                 .getResultList();
    }

    public Almoxarifado findById(Long id) {
        return em.find(Almoxarifado.class, id);
    }

    public void delete(Long id) {
        Almoxarifado a = findById(id);
        if (a != null) em.remove(a);
    }
}
