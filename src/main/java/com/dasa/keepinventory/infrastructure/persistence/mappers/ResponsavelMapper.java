package com.dasa.keepinventory.infrastructure.persistence.mappers;

import com.dasa.keepinventory.domain.entities.Responsavel;
import com.dasa.keepinventory.infrastructure.persistence.entities.ResponsavelJpaEntity;
import org.springframework.stereotype.Component;

@Component
public class ResponsavelMapper {
    
    public ResponsavelJpaEntity toJpa(Responsavel domain) {
        ResponsavelJpaEntity jpa = new ResponsavelJpaEntity();
        jpa.setId(domain.getId());
        jpa.setNome(domain.getInformacao().getNome());
        jpa.setCargo(domain.getInformacao().getCargo());
        return jpa;
    }
    
    public Responsavel toDomain(ResponsavelJpaEntity jpa) {
        return Responsavel.builder()
            .id(jpa.getId())
            .nome(jpa.getNome())
            .cargo(jpa.getCargo())
            .build();
    }
}
