package com.dasa.keepinventory.model;

import com.dasa.keepinventory.model.abstractions.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Column;

@Entity
public class Material extends BaseEntity {

    @Column(nullable = false)
    private String nome;

    @Column
    private String categoria;

    @Column(nullable = false)
    private String unidadeMedida;

    @Column
    private Double quantidadeTotal = 0.0;

    public Material() {}

    public Material(String nome, String categoria, String unidadeMedida, Double quantidadeTotal) {
        this.nome = nome;
        this.categoria = categoria;
        this.unidadeMedida = unidadeMedida;
        this.quantidadeTotal = quantidadeTotal;
    }

    // getters & setters
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

    public String getUnidadeMedida() { return unidadeMedida; }
    public void setUnidadeMedida(String unidadeMedida) { this.unidadeMedida = unidadeMedida; }

    public Double getQuantidadeTotal() { return quantidadeTotal; }
    public void setQuantidadeTotal(Double quantidadeTotal) { this.quantidadeTotal = quantidadeTotal; }
}
