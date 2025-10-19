package com.dasa.keepinventory.api.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

@Schema(description = "Request para criar um novo material")
public class CreateMaterialRequest {
    
    @NotBlank(message = "Nome é obrigatório")
    @Schema(description = "Nome do material", example = "Luvas de Procedimento")
    private String nome;
    
    @Schema(description = "Categoria do material", example = "EPI")
    private String categoria;
    
    @NotBlank(message = "Unidade de medida é obrigatória")
    @Schema(description = "Unidade de medida", example = "UN")
    private String unidadeMedida;
    
    @NotNull(message = "Quantidade inicial é obrigatória")
    @PositiveOrZero(message = "Quantidade deve ser positiva ou zero")
    @Schema(description = "Quantidade inicial", example = "100.0")
    private Double quantidadeInicial;

    // Getters and Setters
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }
    public String getUnidadeMedida() { return unidadeMedida; }
    public void setUnidadeMedida(String unidadeMedida) { this.unidadeMedida = unidadeMedida; }
    public Double getQuantidadeInicial() { return quantidadeInicial; }
    public void setQuantidadeInicial(Double quantidadeInicial) { this.quantidadeInicial = quantidadeInicial; }
}
