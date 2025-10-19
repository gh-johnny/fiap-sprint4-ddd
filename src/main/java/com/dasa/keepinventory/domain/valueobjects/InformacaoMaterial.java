package com.dasa.keepinventory.domain.valueobjects;

import java.util.Objects;

public class InformacaoMaterial {
    private final String nome;
    private final String categoria;
    private final UnidadeMedida unidadeMedida;

    private InformacaoMaterial(String nome, String categoria, UnidadeMedida unidadeMedida) {
        this.nome = nome;
        this.categoria = categoria;
        this.unidadeMedida = unidadeMedida;
    }

    public static InformacaoMaterial of(String nome, String categoria, String unidadeMedida) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do material não pode ser vazio");
        }
        return new InformacaoMaterial(
            nome.trim(), 
            categoria != null ? categoria.trim() : null,
            UnidadeMedida.of(unidadeMedida)
        );
    }

    public String getNome() {
        return nome;
    }

    public String getCategoria() {
        return categoria;
    }

    public UnidadeMedida getUnidadeMedida() {
        return unidadeMedida;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InformacaoMaterial that = (InformacaoMaterial) o;
        return Objects.equals(nome, that.nome) &&
               Objects.equals(categoria, that.categoria) &&
               Objects.equals(unidadeMedida, that.unidadeMedida);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, categoria, unidadeMedida);
    }
}
