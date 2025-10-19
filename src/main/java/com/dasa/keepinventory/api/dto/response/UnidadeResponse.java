package com.dasa.keepinventory.api.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Dados de uma unidade")
public class UnidadeResponse {
    
    @Schema(description = "ID da unidade", example = "1")
    private Long id;
    
    @Schema(description = "Nome da unidade", example = "Hospital Central")
    private String nome;
    
    @Schema(description = "Setor da unidade", example = "Emergência")
    private String setor;

    public UnidadeResponse() {}

    public UnidadeResponse(Long id, String nome, String setor) {
        this.id = id;
        this.nome = nome;
        this.setor = setor;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getSetor() { return setor; }
    public void setSetor(String setor) { this.setor = setor; }
}
