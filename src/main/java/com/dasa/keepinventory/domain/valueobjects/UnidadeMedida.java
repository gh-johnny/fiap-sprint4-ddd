package com.dasa.keepinventory.domain.valueobjects;

import java.util.Objects;

public class UnidadeMedida {
    private final String valor;

    private UnidadeMedida(String valor) {
        this.valor = valor;
    }

    public static UnidadeMedida of(String valor) {
        if (valor == null || valor.trim().isEmpty()) {
            throw new IllegalArgumentException("Unidade de medida não pode ser vazia");
        }
        return new UnidadeMedida(valor.trim().toUpperCase());
    }

    public String getValor() {
        return valor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UnidadeMedida that = (UnidadeMedida) o;
        return Objects.equals(valor, that.valor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(valor);
    }
}
