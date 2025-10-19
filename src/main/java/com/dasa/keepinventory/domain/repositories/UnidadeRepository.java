package com.dasa.keepinventory.domain.repositories;

import com.dasa.keepinventory.domain.entities.Unidade;
import java.util.List;
import java.util.Optional;

public interface UnidadeRepository {
    Unidade save(Unidade unidade);
    Optional<Unidade> findById(Long id);
    List<Unidade> findAll();
    void delete(Long id);
    boolean existsById(Long id);
}

