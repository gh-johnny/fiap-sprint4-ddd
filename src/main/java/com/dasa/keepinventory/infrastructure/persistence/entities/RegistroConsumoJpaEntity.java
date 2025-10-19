package com.dasa.keepinventory.infrastructure.persistence.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "registro_consumo")
public class RegistroConsumoJpaEntity extends BaseJpaEntity {
    
    @Column(nullable = false)
    private Double quantidade;
    
    @Column(nullable = false)
    private LocalDateTime dtConsumo;
    
    @Column
    private String observacao;
    
    @ManyToOne
    @JoinColumn(name = "id_material", nullable = false)
    private MaterialJpaEntity material;
    
    @ManyToOne
    @JoinColumn(name = "id_unidade", nullable = false)
    private UnidadeJpaEntity unidade;
    
    @ManyToOne
    @JoinColumn(name = "id_responsavel", nullable = false)
    private ResponsavelJpaEntity responsavel;

    public RegistroConsumoJpaEntity() {}

    // Getters and Setters
    public Double getQuantidade() { return quantidade; }
    public void setQuantidade(Double quantidade) { this.quantidade = quantidade; }
    public LocalDateTime getDtConsumo() { return dtConsumo; }
    public void setDtConsumo(LocalDateTime dtConsumo) { this.dtConsumo = dtConsumo; }
    public String getObservacao() { return observacao; }
    public void setObservacao(String observacao) { this.observacao = observacao; }
    public MaterialJpaEntity getMaterial() { return material; }
    public void setMaterial(MaterialJpaEntity material) { this.material = material; }
    public UnidadeJpaEntity getUnidade() { return unidade; }
    public void setUnidade(UnidadeJpaEntity unidade) { this.unidade = unidade; }
    public ResponsavelJpaEntity getResponsavel() { return responsavel; }
    public void setResponsavel(ResponsavelJpaEntity responsavel) { this.responsavel = responsavel; }
}
