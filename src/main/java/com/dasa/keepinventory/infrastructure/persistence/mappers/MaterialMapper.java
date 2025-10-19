package com.dasa.keepinventory.infrastructure.persistence.mappers;

import com.dasa.keepinventory.domain.entities.Material;
import com.dasa.keepinventory.infrastructure.persistence.entities.MaterialJpaEntity;
import org.springframework.stereotype.Component;

@Component
public class MaterialMapper {
    
    public MaterialJpaEntity toJpa(Material domain) {
        MaterialJpaEntity jpa = new MaterialJpaEntity();
        jpa.setId(domain.getId());
        jpa.setNome(domain.getInformacao().getNome());
        jpa.setCategoria(domain.getInformacao().getCategoria());
        jpa.setUnidadeMedida(domain.getInformacao().getUnidadeMedida().getValor());
        jpa.setQuantidadeTotal(domain.getQuantidadeTotal().getValor());
        return jpa;
    }
    
    public Material toDomain(MaterialJpaEntity jpa) {
        return Material.builder()
            .id(jpa.getId())
            .nome(jpa.getNome())
            .categoria(jpa.getCategoria())
            .unidadeMedida(jpa.getUnidadeMedida())
            .quantidadeTotal(jpa.getQuantidadeTotal())
            .build();
    }
}
