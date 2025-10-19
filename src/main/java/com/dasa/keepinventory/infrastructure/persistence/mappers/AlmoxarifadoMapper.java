package com.dasa.keepinventory.infrastructure.persistence.mappers;

import com.dasa.keepinventory.domain.entities.Almoxarifado;
import com.dasa.keepinventory.infrastructure.persistence.entities.AlmoxarifadoJpaEntity;
import org.springframework.stereotype.Component;

@Component
public class AlmoxarifadoMapper {
    
    public AlmoxarifadoJpaEntity toJpa(Almoxarifado domain) {
        AlmoxarifadoJpaEntity jpa = new AlmoxarifadoJpaEntity();
        jpa.setId(domain.getId());
        jpa.setLocalizacao(domain.getLocalizacao().getValor());
        return jpa;
    }
    
    public Almoxarifado toDomain(AlmoxarifadoJpaEntity jpa) {
        return Almoxarifado.builder()
            .id(jpa.getId())
            .localizacao(jpa.getLocalizacao())
            .build();
    }
}
