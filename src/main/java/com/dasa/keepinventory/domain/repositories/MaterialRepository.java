package com.dasa.keepinventory.domain.repositories;

import com.dasa.keepinventory.domain.entities.Material;
import java.util.List;
import java.util.Optional;

public interface MaterialRepository {
    Material save(Material material);
    Optional<Material> findById(Long id);
    List<Material> findAll();
    void delete(Long id);
    boolean existsById(Long id);
}
