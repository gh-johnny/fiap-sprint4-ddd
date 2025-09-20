package com.dasa.keepinventory.model;

import java.time.LocalDateTime;
import com.dasa.keepinventory.model.abstractions.BaseEntity;
import jakarta.persistence.*;

@Entity
public class MovimentacaoEstoque extends BaseEntity {

  @Column(nullable = false)
  private String tipoMovimentacao;

  @Column(nullable = false)
  private Double quantidade;

  @Column(nullable = false)
  private LocalDateTime dtMovimentacao;

  @ManyToOne
  @JoinColumn(name = "idMaterial", nullable = false)
  private Material material;

  @ManyToOne
  @JoinColumn(name = "idAlmoxarifado", nullable = false)
  private Almoxarifado almoxarifado;

  @ManyToOne
  @JoinColumn(name = "idResponsavel", nullable = false)
  private Responsavel responsavel;

  public MovimentacaoEstoque() {
  }

  public MovimentacaoEstoque(String tipoMovimentacao, Double quantidade, LocalDateTime dtMovimentacao,
      Material material, Almoxarifado almoxarifado, Responsavel responsavel) {
    this.tipoMovimentacao = tipoMovimentacao;
    this.quantidade = quantidade;
    this.dtMovimentacao = dtMovimentacao;
    this.material = material;
    this.almoxarifado = almoxarifado;
    this.responsavel = responsavel;
  }

  public String getTipoMovimentacao() {
    return tipoMovimentacao;
  }

  public Double getQuantidade() {
    return quantidade;
  }

  public LocalDateTime getDtMovimentacao() {
    return dtMovimentacao;
  }

  public Material getMaterial() {
    return material;
  }

  public Almoxarifado getAlmoxarifado() {
    return almoxarifado;
  }

  public Responsavel getResponsavel() {
    return responsavel;
  }
}
