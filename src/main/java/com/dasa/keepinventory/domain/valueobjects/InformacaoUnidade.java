package com.dasa.keepinventory.domain.valueobjects;

import java.util.Objects;

public class InformacaoUnidade {
    private final String nome;
    private final String setor;

    private InformacaoUnidade(String nome, String setor) {
        this.nome = nome;
        this.setor = setor;
    }

    public static InformacaoUnidade of(String nome, String setor) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome da unidade não pode ser vazio");
        }
        return new InformacaoUnidade(nome.trim(), setor != null ? setor.trim() : null);
    }

    public String getNome() {
        return nome;
    }

    public String getSetor() {
        return setor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InformacaoUnidade that = (InformacaoUnidade) o;
        return Objects.equals(nome, that.nome) &&
               Objects.equals(setor, that.setor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, setor);
    }
}
