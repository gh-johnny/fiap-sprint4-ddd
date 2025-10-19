package com.dasa.keepinventory.api.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Request para criar um novo responsável")
public class CreateResponsavelRequest {
    
    @NotBlank(message = "Nome é obrigatório")
    @Schema(description = "Nome do responsável", example = "João Silva")
    private String nome;
    
    @Schema(description = "Cargo do responsável", example = "Técnico de Enfermagem")
    private String cargo;

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getCargo() { return cargo; }
    public void setCargo(String cargo) { this.cargo = cargo; }
}
