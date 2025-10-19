package com.dasa.keepinventory.domain.valueobjects;

import java.util.Objects;

public class InformacaoPessoa {
    private final String nome;
    private final String cargo;

    private InformacaoPessoa(String nome, String cargo) {
        this.nome = nome;
        this.cargo = cargo;
    }

    public static InformacaoPessoa of(String nome, String cargo) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome não pode ser vazio");
        }
        return new InformacaoPessoa(nome.trim(), cargo != null ? cargo.trim() : null);
    }

    public String getNome() {
        return nome;
    }

    public String getCargo() {
        return cargo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InformacaoPessoa that = (InformacaoPessoa) o;
        return Objects.equals(nome, that.nome) &&
               Objects.equals(cargo, that.cargo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, cargo);
    }
}
