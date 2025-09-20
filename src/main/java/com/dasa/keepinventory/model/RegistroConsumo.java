package com.dasa.keepinventory.model;

import jakarta.persistence.Column;
import java.time.LocalDateTime;
import com.dasa.keepinventory.model.abstractions.BaseEntity;
import jakarta.persistence.*;

@Entity
public class RegistroConsumo extends BaseEntity {

  @Column(nullable = false)
  private Double quantidade;

  @Column(nullable = false)
  private LocalDateTime dtConsumo;

  @Column
  private String observacao;

  @ManyToOne
  @JoinColumn(name = "idMaterial", nullable = false)
  private Material material;

  @ManyToOne
  @JoinColumn(name = "idUnidade", nullable = false)
  private Unidade unidade;

  @ManyToOne
  @JoinColumn(name = "idResponsavel", nullable = false)
  private Responsavel responsavel;

  public RegistroConsumo() {
  }

  public RegistroConsumo(Double quantidade, LocalDateTime dtConsumo, String observacao,
      Material material, Unidade unidade, Responsavel responsavel) {
    this.quantidade = quantidade;
    this.dtConsumo = dtConsumo;
    this.observacao = observacao;
    this.material = material;
    this.unidade = unidade;
    this.responsavel = responsavel;
  }

  @Override
  public Long getId() {
    return super.getId();
  }

  public Double getQuantidade() {
    return quantidade;
  }

  public void setQuantidade(Double quantidade) {
    this.quantidade = quantidade;
  }

  public LocalDateTime getDtConsumo() {
    return dtConsumo;
  }

  public String getObservacao() {
    return observacao;
  }

  public Material getMaterial() {
    return material;
  }

  public void setMaterial(Material material) {
    this.material = material;
  }

  public Unidade getUnidade() {
    return unidade;
  }

  public Responsavel getResponsavel() {
    return responsavel;
  }
}
