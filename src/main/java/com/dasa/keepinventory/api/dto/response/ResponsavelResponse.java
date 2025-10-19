package com.dasa.keepinventory.api.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Dados de um responsável")
public class ResponsavelResponse {
    
    @Schema(description = "ID do responsável", example = "1")
    private Long id;
    
    @Schema(description = "Nome do responsável", example = "João Silva")
    private String nome;
    
    @Schema(description = "Cargo do responsável", example = "Técnico de Enfermagem")
    private String cargo;

    public ResponsavelResponse() {}

    public ResponsavelResponse(Long id, String nome, String cargo) {
        this.id = id;
        this.nome = nome;
        this.cargo = cargo;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getCargo() { return cargo; }
    public void setCargo(String cargo) { this.cargo = cargo; }
}
