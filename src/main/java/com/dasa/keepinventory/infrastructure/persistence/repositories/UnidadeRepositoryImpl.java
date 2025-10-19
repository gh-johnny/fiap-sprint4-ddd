package com.dasa.keepinventory.infrastructure.persistence.repositories;

import com.dasa.keepinventory.domain.entities.Unidade;
import com.dasa.keepinventory.domain.repositories.UnidadeRepository;
import com.dasa.keepinventory.infrastructure.persistence.jpa.UnidadeJpaRepository;
import com.dasa.keepinventory.infrastructure.persistence.mappers.UnidadeMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class UnidadeRepositoryImpl implements UnidadeRepository {
    
    private final UnidadeJpaRepository jpaRepository;
    private final UnidadeMapper mapper;

    public UnidadeRepositoryImpl(UnidadeJpaRepository jpaRepository, UnidadeMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Unidade save(Unidade unidade) {
        var jpaEntity = mapper.toJpa(unidade);
        var saved = jpaRepository.save(jpaEntity);
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<Unidade> findById(Long id) {
        return jpaRepository.findById(id)
            .map(mapper::toDomain);
    }

    @Override
    public List<Unidade> findAll() {
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
