package com.dasa.keepinventory.infrastructure.persistence.mappers;

import com.dasa.keepinventory.domain.aggregates.MovimentacaoEstoque;
import com.dasa.keepinventory.domain.valueobjects.Quantidade;
import com.dasa.keepinventory.infrastructure.persistence.entities.MovimentacaoEstoqueJpaEntity;
import org.springframework.stereotype.Component;

@Component
public class MovimentacaoEstoqueMapper {
    
    private final MaterialMapper materialMapper;
    private final AlmoxarifadoMapper almoxarifadoMapper;
    private final ResponsavelMapper responsavelMapper;

    public MovimentacaoEstoqueMapper(MaterialMapper materialMapper,
                                    AlmoxarifadoMapper almoxarifadoMapper,
                                    ResponsavelMapper responsavelMapper) {
        this.materialMapper = materialMapper;
        this.almoxarifadoMapper = almoxarifadoMapper;
        this.responsavelMapper = responsavelMapper;
    }

    public MovimentacaoEstoqueJpaEntity toJpa(MovimentacaoEstoque domain) {
        MovimentacaoEstoqueJpaEntity jpa = new MovimentacaoEstoqueJpaEntity();
        jpa.setId(domain.getId());
        jpa.setTipoMovimentacao(domain.getTipo().name());
        jpa.setQuantidade(domain.getQuantidade().getValor());
        jpa.setDtMovimentacao(domain.getDataMovimentacao());
        jpa.setMaterial(materialMapper.toJpa(domain.getMaterial()));
        jpa.setAlmoxarifado(almoxarifadoMapper.toJpa(domain.getAlmoxarifado()));
        jpa.setResponsavel(responsavelMapper.toJpa(domain.getResponsavel()));
        return jpa;
    }
    
    public MovimentacaoEstoque toDomain(MovimentacaoEstoqueJpaEntity jpa) {
        return MovimentacaoEstoque.reconstituir(
            jpa.getId(),
            MovimentacaoEstoque.TipoMovimentacao.fromString(jpa.getTipoMovimentacao()),
            Quantidade.of(jpa.getQuantidade()),
            jpa.getDtMovimentacao(),
            materialMapper.toDomain(jpa.getMaterial()),
            almoxarifadoMapper.toDomain(jpa.getAlmoxarifado()),
            responsavelMapper.toDomain(jpa.getResponsavel())
        );
    }
}
