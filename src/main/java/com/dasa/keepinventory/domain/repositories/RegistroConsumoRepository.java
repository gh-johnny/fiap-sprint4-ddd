package com.dasa.keepinventory.domain.repositories;

import com.dasa.keepinventory.domain.aggregates.RegistroConsumo;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface RegistroConsumoRepository {
    RegistroConsumo save(RegistroConsumo registro);
    Optional<RegistroConsumo> findById(Long id);
    List<RegistroConsumo> findAll();
    List<RegistroConsumo> findByMaterialId(Long materialId);
    List<RegistroConsumo> findByUnidadeId(Long unidadeId);
    List<RegistroConsumo> findByPeriodo(LocalDateTime inicio, LocalDateTime fim);
    void delete(Long id);
}
