package com.dasa.keepinventory.infrastructure.persistence.jpa;

import com.dasa.keepinventory.infrastructure.persistence.entities.MovimentacaoEstoqueJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MovimentacaoEstoqueJpaRepository extends JpaRepository<MovimentacaoEstoqueJpaEntity, Long> {
    
    List<MovimentacaoEstoqueJpaEntity> findByMaterialId(Long materialId);
    
    @Query("SELECT m FROM MovimentacaoEstoqueJpaEntity m WHERE m.dtMovimentacao BETWEEN :inicio AND :fim")
    List<MovimentacaoEstoqueJpaEntity> findByPeriodo(@Param("inicio") LocalDateTime inicio, @Param("fim") LocalDateTime fim);
}
