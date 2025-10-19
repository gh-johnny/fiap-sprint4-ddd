package com.dasa.keepinventory.infrastructure.persistence.repositories;

import com.dasa.keepinventory.domain.entities.Material;
import com.dasa.keepinventory.domain.repositories.MaterialRepository;
import com.dasa.keepinventory.infrastructure.persistence.jpa.MaterialJpaRepository;
import com.dasa.keepinventory.infrastructure.persistence.mappers.MaterialMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class MaterialRepositoryImpl implements MaterialRepository {
    
    private final MaterialJpaRepository jpaRepository;
    private final MaterialMapper mapper;

    public MaterialRepositoryImpl(MaterialJpaRepository jpaRepository, MaterialMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Material save(Material material) {
        var jpaEntity = mapper.toJpa(material);
        var saved = jpaRepository.save(jpaEntity);
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<Material> findById(Long id) {
        return jpaRepository.findById(id)
            .map(mapper::toDomain);
    }

    @Override
    public List<Material> findAll() {
        return jpaRepository.findAll().stream()
            .map(mapper::toDomain)
            .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        jpaRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return jpaRepository.existsById(id);
    }
}
