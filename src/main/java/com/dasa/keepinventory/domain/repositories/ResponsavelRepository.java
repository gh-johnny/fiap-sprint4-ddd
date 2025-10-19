package com.dasa.keepinventory.domain.repositories;

import com.dasa.keepinventory.domain.entities.Responsavel;
import java.util.List;
import java.util.Optional;

public interface ResponsavelRepository {
    Responsavel save(Responsavel responsavel);
    Optional<Responsavel> findById(Long id);
    List<Responsavel> findAll();
    void delete(Long id);
    boolean existsById(Long id);
}
