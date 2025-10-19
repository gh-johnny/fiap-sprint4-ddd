package com.dasa.keepinventory.api.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Dados de um almoxarifado")
public class AlmoxarifadoResponse {
    
    @Schema(description = "ID do almoxarifado", example = "1")
    private Long id;
    
    @Schema(description = "Localização do almoxarifado", example = "Prédio A - Sala 101")
    private String localizacao;

    public AlmoxarifadoResponse() {}

    public AlmoxarifadoResponse(Long id, String localizacao) {
        this.id = id;
        this.localizacao = localizacao;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getLocalizacao() { return localizacao; }
    public void setLocalizacao(String localizacao) { this.localizacao = localizacao; }
}
