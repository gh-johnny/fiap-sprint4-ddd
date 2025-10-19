package com.dasa.keepinventory.api.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Schema(description = "Request para registrar movimentação de estoque")
public class StockMovementRequest {
    
    @NotNull(message = "ID do material é obrigatório")
    @Schema(description = "ID do material", example = "1")
    private Long idMaterial;
    
    @NotNull(message = "ID do almoxarifado é obrigatório")
    @Schema(description = "ID do almoxarifado", example = "1")
    private Long idAlmoxarifado;
    
    @NotNull(message = "ID do responsável é obrigatório")
    @Schema(description = "ID do responsável", example = "1")
    private Long idResponsavel;
    
    @NotNull(message = "Quantidade é obrigatória")
    @Positive(message = "Quantidade deve ser positiva")
    @Schema(description = "Quantidade da movimentação", example = "50.0")
    private Double quantidade;

    // Getters and Setters
    public Long getIdMaterial() { return idMaterial; }
    public void setIdMaterial(Long idMaterial) { this.idMaterial = idMaterial; }
    public Long getIdAlmoxarifado() { return idAlmoxarifado; }
    public void setIdAlmoxarifado(Long idAlmoxarifado) { this.idAlmoxarifado = idAlmoxarifado; }
    public Long getIdResponsavel() { return idResponsavel; }
    public void setIdResponsavel(Long idResponsavel) { this.idResponsavel = idResponsavel; }
    public Double getQuantidade() { return quantidade; }
    public void setQuantidade(Double quantidade) { this.quantidade = quantidade; }
}
