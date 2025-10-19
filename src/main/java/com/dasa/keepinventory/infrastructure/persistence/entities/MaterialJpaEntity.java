package com.dasa.keepinventory.infrastructure.persistence.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "material")
public class MaterialJpaEntity extends BaseJpaEntity {
    
    @Column(nullable = false)
    private String nome;
    
    @Column
    private String categoria;
    
    @Column(nullable = false)
    private String unidadeMedida;
    
    @Column(nullable = false)
    private Double quantidadeTotal;

    public MaterialJpaEntity() {}

    public MaterialJpaEntity(String nome, String categoria, String unidadeMedida, Double quantidadeTotal) {
        this.nome = nome;
        this.categoria = categoria;
        this.unidadeMedida = unidadeMedida;
        this.quantidadeTotal = quantidadeTotal;
    }

    // Getters and Setters
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }
    public String getUnidadeMedida() { return unidadeMedida; }
    public void setUnidadeMedida(String unidadeMedida) { this.unidadeMedida = unidadeMedida; }
    public Double getQuantidadeTotal() { return quantidadeTotal; }
    public void setQuantidadeTotal(Double quantidadeTotal) { this.quantidadeTotal = quantidadeTotal; }
}
