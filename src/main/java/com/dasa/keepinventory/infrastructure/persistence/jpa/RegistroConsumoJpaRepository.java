package com.dasa.keepinventory.infrastructure.persistence.jpa;

import com.dasa.keepinventory.infrastructure.persistence.entities.RegistroConsumoJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface RegistroConsumoJpaRepository extends JpaRepository<RegistroConsumoJpaEntity, Long> {
    
    List<RegistroConsumoJpaEntity> findByMaterialId(Long materialId);
    
    List<RegistroConsumoJpaEntity> findByUnidadeId(Long unidadeId);
    
    @Query("SELECT r FROM RegistroConsumoJpaEntity r WHERE r.dtConsumo BETWEEN :inicio AND :fim")
    List<RegistroConsumoJpaEntity> findByPeriodo(@Param("inicio") LocalDateTime inicio, @Param("fim") LocalDateTime fim);
}
