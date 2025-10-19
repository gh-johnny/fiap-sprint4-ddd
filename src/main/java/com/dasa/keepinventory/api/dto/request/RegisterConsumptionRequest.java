package com.dasa.keepinventory.api.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Schema(description = "Request para registrar consumo de material")
public class RegisterConsumptionRequest {
    
    @NotNull(message = "ID do material é obrigatório")
    @Schema(description = "ID do material", example = "1")
    private Long idMaterial;
    
    @NotNull(message = "ID da unidade é obrigatório")
    @Schema(description = "ID da unidade", example = "1")
    private Long idUnidade;
    
    @NotNull(message = "ID do responsável é obrigatório")
    @Schema(description = "ID do responsável", example = "1")
    private Long idResponsavel;
    
    @NotNull(message = "Quantidade é obrigatória")
    @Positive(message = "Quantidade deve ser positiva")
    @Schema(description = "Quantidade consumida", example = "10.0")
    private Double quantidade;
    
    @Schema(description = "Observação sobre o consumo", example = "Procedimento cirúrgico")
    private String observacao;

    // Getters and Setters
    public Long getIdMaterial() { return idMaterial; }
    public void setIdMaterial(Long idMaterial) { this.idMaterial = idMaterial; }
    public Long getIdUnidade() { return idUnidade; }
    public void setIdUnidade(Long idUnidade) { this.idUnidade = idUnidade; }
    public Long getIdResponsavel() { return idResponsavel; }
    public void setIdResponsavel(Long idResponsavel) { this.idResponsavel = idResponsavel; }
    public Double getQuantidade() { return quantidade; }
    public void setQuantidade(Double quantidade) { this.quantidade = quantidade; }
    public String getObservacao() { return observacao; }
    public void setObservacao(String observacao) { this.observacao = observacao; }
}
