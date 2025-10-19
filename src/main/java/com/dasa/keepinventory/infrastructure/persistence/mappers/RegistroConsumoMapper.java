package com.dasa.keepinventory.infrastructure.persistence.mappers;

import com.dasa.keepinventory.domain.aggregates.RegistroConsumo;
import com.dasa.keepinventory.domain.valueobjects.Quantidade;
import com.dasa.keepinventory.infrastructure.persistence.entities.RegistroConsumoJpaEntity;
import org.springframework.stereotype.Component;

@Component
public class RegistroConsumoMapper {
    
    private final MaterialMapper materialMapper;
    private final UnidadeMapper unidadeMapper;
    private final ResponsavelMapper responsavelMapper;

    public RegistroConsumoMapper(MaterialMapper materialMapper,
                                UnidadeMapper unidadeMapper,
                                ResponsavelMapper responsavelMapper) {
        this.materialMapper = materialMapper;
        this.unidadeMapper = unidadeMapper;
        this.responsavelMapper = responsavelMapper;
    }

    public RegistroConsumoJpaEntity toJpa(RegistroConsumo domain) {
        RegistroConsumoJpaEntity jpa = new RegistroConsumoJpaEntity();
        jpa.setId(domain.getId());
        jpa.setQuantidade(domain.getQuantidade().getValor());
        jpa.setDtConsumo(domain.getDataConsumo());
        jpa.setObservacao(domain.getObservacao());
        jpa.setMaterial(materialMapper.toJpa(domain.getMaterial()));
        jpa.setUnidade(unidadeMapper.toJpa(domain.getUnidade()));
        jpa.setResponsavel(responsavelMapper.toJpa(domain.getResponsavel()));
        return jpa;
    }
    
    public RegistroConsumo toDomain(RegistroConsumoJpaEntity jpa) {
        return RegistroConsumo.reconstituir(
            jpa.getId(),
            Quantidade.of(jpa.getQuantidade()),
            jpa.getDtConsumo(),
            jpa.getObservacao(),
            materialMapper.toDomain(jpa.getMaterial()),
            unidadeMapper.toDomain(jpa.getUnidade()),
            responsavelMapper.toDomain(jpa.getResponsavel())
        );
    }
}
