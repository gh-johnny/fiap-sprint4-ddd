package com.dasa.keepinventory.infrastructure.persistence.jpa;

import com.dasa.keepinventory.infrastructure.persistence.entities.UnidadeJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnidadeJpaRepository extends JpaRepository<UnidadeJpaEntity, Long> {
}
