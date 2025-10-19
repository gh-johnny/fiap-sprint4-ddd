package com.dasa.keepinventory.infrastructure.persistence.repositories;

import com.dasa.keepinventory.domain.entities.Almoxarifado;
import com.dasa.keepinventory.domain.repositories.AlmoxarifadoRepository;
import com.dasa.keepinventory.infrastructure.persistence.jpa.AlmoxarifadoJpaRepository;
import com.dasa.keepinventory.infrastructure.persistence.mappers.AlmoxarifadoMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class AlmoxarifadoRepositoryImpl implements AlmoxarifadoRepository {
    
    private final AlmoxarifadoJpaRepository jpaRepository;
    private final AlmoxarifadoMapper mapper;

    public AlmoxarifadoRepositoryImpl(AlmoxarifadoJpaRepository jpaRepository, AlmoxarifadoMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Almoxarifado save(Almoxarifado almoxarifado) {
        var jpaEntity = mapper.toJpa(almoxarifado);
        var saved = jpaRepository.save(jpaEntity);
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<Almoxarifado> findById(Long id) {
        return jpaRepository.findById(id)
            .map(mapper::toDomain);
    }

    @Override
    public List<Almoxarifado> findAll() {
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
