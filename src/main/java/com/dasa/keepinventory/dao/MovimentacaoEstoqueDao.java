package com.dasa.keepinventory.dao;

import com.dasa.keepinventory.model.MovimentacaoEstoque;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class MovimentacaoEstoqueDao {

    @PersistenceContext
    private EntityManager em;

    public MovimentacaoEstoque save(MovimentacaoEstoque m) {
        em.persist(m);
        return m;
    }

    public List<MovimentacaoEstoque> findAll() {
        return em.createQuery("SELECT m FROM MovimentacaoEstoque m", MovimentacaoEstoque.class)
                 .getResultList();
    }

    public MovimentacaoEstoque findById(Long id) {
        return em.find(MovimentacaoEstoque.class, id);
    }

    public void delete(Long id) {
        MovimentacaoEstoque m = findById(id);
        if (m != null) em.remove(m);
    }
}
