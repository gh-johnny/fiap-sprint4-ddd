package com.dasa.keepinventory.api.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Request para criar um novo almoxarifado")
public class CreateAlmoxarifadoRequest {
    
    @NotBlank(message = "Localização é obrigatória")
    @Schema(description = "Localização do almoxarifado", example = "Prédio A - Sala 101")
    private String localizacao;

    public String getLocalizacao() { return localizacao; }
    public void setLocalizacao(String localizacao) { this.localizacao = localizacao; }
}
