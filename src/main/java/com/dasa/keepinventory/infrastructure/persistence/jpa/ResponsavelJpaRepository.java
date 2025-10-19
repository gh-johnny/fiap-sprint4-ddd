package com.dasa.keepinventory.infrastructure.persistence.jpa;

import com.dasa.keepinventory.infrastructure.persistence.entities.ResponsavelJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResponsavelJpaRepository extends JpaRepository<ResponsavelJpaEntity, Long> {
}
