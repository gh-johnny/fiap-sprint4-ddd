package com.dasa.keepinventory.infrastructure.persistence.repositories;

import com.dasa.keepinventory.domain.aggregates.MovimentacaoEstoque;
import com.dasa.keepinventory.domain.repositories.MovimentacaoEstoqueRepository;
import com.dasa.keepinventory.infrastructure.persistence.jpa.MovimentacaoEstoqueJpaRepository;
import com.dasa.keepinventory.infrastructure.persistence.mappers.MovimentacaoEstoqueMapper;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class MovimentacaoEstoqueRepositoryImpl implements MovimentacaoEstoqueRepository {
    
    private final MovimentacaoEstoqueJpaRepository jpaRepository;
    private final MovimentacaoEstoqueMapper mapper;

    public MovimentacaoEstoqueRepositoryImpl(MovimentacaoEstoqueJpaRepository jpaRepository, 
                                            MovimentacaoEstoqueMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public MovimentacaoEstoque save(MovimentacaoEstoque movimentacao) {
        var jpaEntity = mapper.toJpa(movimentacao);
        var saved = jpaRepository.save(jpaEntity);
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<MovimentacaoEstoque> findById(Long id) {
        return jpaRepository.findById(id)
            .map(mapper::toDomain);
    }

    @Override
    public List<MovimentacaoEstoque> findAll() {
        return jpaRepository.findAll().stream()
            .map(mapper::toDomain)
            .collect(Collectors.toList());
    }

    @Override
    public List<MovimentacaoEstoque> findByMaterialId(Long materialId) {
        return jpaRepository.findByMaterialId(materialId).stream()
            .map(mapper::toDomain)
            .collect(Collectors.toList());
    }

    @Override
    public List<MovimentacaoEstoque> findByPeriodo(LocalDateTime inicio, LocalDateTime fim) {
        return jpaRepository.findByPeriodo(inicio, fim).stream()
            .map(mapper::toDomain)
            .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        jpaRepository.deleteById(id);
    }
}
