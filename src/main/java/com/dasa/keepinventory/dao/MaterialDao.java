package com.dasa.keepinventory.dao;

import com.dasa.keepinventory.model.Material;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class MaterialDao {

    @PersistenceContext
    private EntityManager em;

    public Material save(Material m) {
        em.persist(m);
        return m;
    }

    public List<Material> findAll() {
        return em.createQuery("SELECT m FROM Material m", Material.class)
                 .getResultList();
    }

    public Material findById(Long id) {
        return em.find(Material.class, id);
    }

    public void delete(Long id) {
        Material m = findById(id);
        if (m != null) em.remove(m);
    }
}
