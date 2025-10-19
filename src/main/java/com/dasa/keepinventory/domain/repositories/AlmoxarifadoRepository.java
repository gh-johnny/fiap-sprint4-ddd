package com.dasa.keepinventory.domain.repositories;

import com.dasa.keepinventory.domain.entities.Almoxarifado;
import java.util.List;
import java.util.Optional;

public interface AlmoxarifadoRepository {
    Almoxarifado save(Almoxarifado almoxarifado);
    Optional<Almoxarifado> findById(Long id);
    List<Almoxarifado> findAll();
    void delete(Long id);
    boolean existsById(Long id);
}
