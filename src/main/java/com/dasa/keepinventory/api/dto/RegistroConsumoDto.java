package com.dasa.keepinventory.api.dto;

import java.time.LocalDateTime;

public class RegistroConsumoDto {
    public Double quantidade;
    public LocalDateTime dtConsumo;
    public String observacao;
    public Long idMaterial;
    public Long idUnidade;
    public Long idResponsavel;
}
