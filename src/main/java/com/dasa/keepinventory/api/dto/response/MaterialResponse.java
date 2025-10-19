package com.dasa.keepinventory.api.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Dados de um material")
public class MaterialResponse {
    
    @Schema(description = "ID do material", example = "1")
    private Long id;
    
    @Schema(description = "Nome do material", example = "Luvas de Procedimento")
    private String nome;
    
    @Schema(description = "Categoria do material", example = "EPI")
    private String categoria;
    
    @Schema(description = "Unidade de medida", example = "UN")
    private String unidadeMedida;
    
    @Schema(description = "Quantidade total em estoque", example = "100.0")
    private Double quantidadeTotal;

    public MaterialResponse() {}

    public MaterialResponse(Long id, String nome, String categoria, String unidadeMedida, Double quantidadeTotal) {
        this.id = id;
        this.nome = nome;
        this.categoria = categoria;
        this.unidadeMedida = unidadeMedida;
        this.quantidadeTotal = quantidadeTotal;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }
    public String getUnidadeMedida() { return unidadeMedida; }
    public void setUnidadeMedida(String unidadeMedida) { this.unidadeMedida = unidadeMedida; }
    public Double getQuantidadeTotal() { return quantidadeTotal; }
    public void setQuantidadeTotal(Double quantidadeTotal) { this.quantidadeTotal = quantidadeTotal; }
}
