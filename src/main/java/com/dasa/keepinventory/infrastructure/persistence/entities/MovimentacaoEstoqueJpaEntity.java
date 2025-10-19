package com.dasa.keepinventory.infrastructure.persistence.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "movimentacao_estoque")
public class MovimentacaoEstoqueJpaEntity extends BaseJpaEntity {
    
    @Column(nullable = false)
    private String tipoMovimentacao;
    
    @Column(nullable = false)
    private Double quantidade;
    
    @Column(nullable = false)
    private LocalDateTime dtMovimentacao;
    
    @ManyToOne
    @JoinColumn(name = "id_material", nullable = false)
    private MaterialJpaEntity material;
    
    @ManyToOne
    @JoinColumn(name = "id_almoxarifado", nullable = false)
    private AlmoxarifadoJpaEntity almoxarifado;
    
    @ManyToOne
    @JoinColumn(name = "id_responsavel", nullable = false)
    private ResponsavelJpaEntity responsavel;

    public MovimentacaoEstoqueJpaEntity() {}

    // Getters and Setters
    public String getTipoMovimentacao() { return tipoMovimentacao; }
    public void setTipoMovimentacao(String tipoMovimentacao) { this.tipoMovimentacao = tipoMovimentacao; }
    public Double getQuantidade() { return quantidade; }
    public void setQuantidade(Double quantidade) { this.quantidade = quantidade; }
    public LocalDateTime getDtMovimentacao() { return dtMovimentacao; }
    public void setDtMovimentacao(LocalDateTime dtMovimentacao) { this.dtMovimentacao = dtMovimentacao; }
    public MaterialJpaEntity getMaterial() { return material; }
    public void setMaterial(MaterialJpaEntity material) { this.material = material; }
    public AlmoxarifadoJpaEntity getAlmoxarifado() { return almoxarifado; }
    public void setAlmoxarifado(AlmoxarifadoJpaEntity almoxarifado) { this.almoxarifado = almoxarifado; }
    public ResponsavelJpaEntity getResponsavel() { return responsavel; }
    public void setResponsavel(ResponsavelJpaEntity responsavel) { this.responsavel = responsavel; }
}
