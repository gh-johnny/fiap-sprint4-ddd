package com.dasa.keepinventory.infrastructure.persistence.repositories;

import com.dasa.keepinventory.domain.entities.Responsavel;
import com.dasa.keepinventory.domain.repositories.ResponsavelRepository;
import com.dasa.keepinventory.infrastructure.persistence.jpa.ResponsavelJpaRepository;
import com.dasa.keepinventory.infrastructure.persistence.mappers.ResponsavelMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class ResponsavelRepositoryImpl implements ResponsavelRepository {
    
    private final ResponsavelJpaRepository jpaRepository;
    private final ResponsavelMapper mapper;

    public ResponsavelRepositoryImpl(ResponsavelJpaRepository jpaRepository, ResponsavelMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Responsavel save(Responsavel responsavel) {
        var jpaEntity = mapper.toJpa(responsavel);
        var saved = jpaRepository.save(jpaEntity);
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<Responsavel> findById(Long id) {
        return jpaRepository.findById(id)
            .map(mapper::toDomain);
    }

    @Override
    public List<Responsavel> findAll() {
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
