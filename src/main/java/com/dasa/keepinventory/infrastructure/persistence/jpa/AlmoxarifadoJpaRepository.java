package com.dasa.keepinventory.infrastructure.persistence.jpa;

import com.dasa.keepinventory.infrastructure.persistence.entities.AlmoxarifadoJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlmoxarifadoJpaRepository extends JpaRepository<AlmoxarifadoJpaEntity, Long> {
}
