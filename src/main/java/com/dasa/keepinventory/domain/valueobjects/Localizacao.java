package com.dasa.keepinventory.domain.valueobjects;

import java.util.Objects;

public class Localizacao {
    private final String valor;

    private Localizacao(String valor) {
        this.valor = valor;
    }

    public static Localizacao of(String valor) {
        if (valor == null || valor.trim().isEmpty()) {
            throw new IllegalArgumentException("Localização não pode ser vazia");
        }
        return new Localizacao(valor.trim());
    }

    public String getValor() {
        return valor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Localizacao that = (Localizacao) o;
        return Objects.equals(valor, that.valor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(valor);
    }
}
