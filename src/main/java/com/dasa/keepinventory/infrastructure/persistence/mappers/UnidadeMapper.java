package com.dasa.keepinventory.infrastructure.persistence.mappers;

import com.dasa.keepinventory.domain.entities.Unidade;
import com.dasa.keepinventory.infrastructure.persistence.entities.UnidadeJpaEntity;
import org.springframework.stereotype.Component;

@Component
public class UnidadeMapper {
    
    public UnidadeJpaEntity toJpa(Unidade domain) {
        UnidadeJpaEntity jpa = new UnidadeJpaEntity();
        jpa.setId(domain.getId());
        jpa.setNome(domain.getInformacao().getNome());
        jpa.setSetor(domain.getInformacao().getSetor());
        return jpa;
    }
    
    public Unidade toDomain(UnidadeJpaEntity jpa) {
        return Unidade.builder()
            .id(jpa.getId())
            .nome(jpa.getNome())
            .setor(jpa.getSetor())
            .build();
    }
}
