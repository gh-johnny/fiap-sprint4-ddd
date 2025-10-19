package com.dasa.keepinventory.infrastructure.persistence.repositories;

import com.dasa.keepinventory.domain.aggregates.RegistroConsumo;
import com.dasa.keepinventory.domain.repositories.RegistroConsumoRepository;
import com.dasa.keepinventory.infrastructure.persistence.jpa.RegistroConsumoJpaRepository;
import com.dasa.keepinventory.infrastructure.persistence.mappers.RegistroConsumoMapper;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class RegistroConsumoRepositoryImpl implements RegistroConsumoRepository {
    
    private final RegistroConsumoJpaRepository jpaRepository;
    private final RegistroConsumoMapper mapper;

    public RegistroConsumoRepositoryImpl(RegistroConsumoJpaRepository jpaRepository, 
                                        RegistroConsumoMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public RegistroConsumo save(RegistroConsumo registro) {
        var jpaEntity = mapper.toJpa(registro);
        var saved = jpaRepository.save(jpaEntity);
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<RegistroConsumo> findById(Long id) {
        return jpaRepository.findById(id)
            .map(mapper::toDomain);
    }

    @Override
    public List<RegistroConsumo> findAll() {
        return jpaRepository.findAll().stream()
            .map(mapper::toDomain)
            .collect(Collectors.toList());
    }

    @Override
    public List<RegistroConsumo> findByMaterialId(Long materialId) {
        return jpaRepository.findByMaterialId(materialId).stream()
            .map(mapper::toDomain)
            .collect(Collectors.toList());
    }

    @Override
    public List<RegistroConsumo> findByUnidadeId(Long unidadeId) {
        return jpaRepository.findByUnidadeId(unidadeId).stream()
            .map(mapper::toDomain)
            .collect(Collectors.toList());
    }

    @Override
    public List<RegistroConsumo> findByPeriodo(LocalDateTime inicio, LocalDateTime fim) {
        return jpaRepository.findByPeriodo(inicio, fim).stream()
            .map(mapper::toDomain)
            .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        jpaRepository.deleteById(id);
    }
}
