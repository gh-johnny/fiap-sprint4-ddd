package com.dasa.keepinventory.api.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Request para criar uma nova unidade")
public class CreateUnidadeRequest {
    
    @NotBlank(message = "Nome é obrigatório")
    @Schema(description = "Nome da unidade", example = "Hospital Central")
    private String nome;
    
    @Schema(description = "Setor da unidade", example = "Emergência")
    private String setor;

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getSetor() { return setor; }
    public void setSetor(String setor) { this.setor = setor; }
}
