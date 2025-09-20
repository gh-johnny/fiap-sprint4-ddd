package com.dasa.keepinventory.api.dto;

import java.time.LocalDateTime;

public class MovimentacaoEstoqueDto {
  public String tipoMovimentacao;
  public Double quantidade;
  public LocalDateTime dtMovimentacao;
  public Long idMaterial;
  public Long idAlmoxarifado;
  public Long idResponsavel;
}
