package com.dasa.keepinventory.domain.valueobjects;

import java.util.Objects;

public class Quantidade {
    private final Double valor;

    private Quantidade(Double valor) {
        this.valor = valor;
    }

    public static Quantidade of(Double valor) {
        if (valor == null || valor < 0) {
            throw new IllegalArgumentException("Quantidade não pode ser negativa");
        }
        return new Quantidade(valor);
    }

    public static Quantidade zero() {
        return new Quantidade(0.0);
    }

    public Quantidade adicionar(Quantidade outra) {
        return new Quantidade(this.valor + outra.valor);
    }

    public Quantidade subtrair(Quantidade outra) {
        double resultado = this.valor - outra.valor;
        if (resultado < 0) {
            throw new IllegalArgumentException("Quantidade resultante não pode ser negativa");
        }
        return new Quantidade(resultado);
    }

    public boolean menorQue(Quantidade outra) {
        return this.valor < outra.valor;
    }

    public boolean maiorOuIgualA(Quantidade outra) {
        return this.valor >= outra.valor;
    }

    public Double getValor() {
        return valor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Quantidade that = (Quantidade) o;
        return Objects.equals(valor, that.valor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(valor);
    }
}
