package com.dasa.keepinventory.domain.specifications.base;

public class OuSpecification<T> implements Specification<T> {
    private final Specification<T> esquerda;
    private final Specification<T> direita;

    public OuSpecification(Specification<T> esquerda, Specification<T> direita) {
        this.esquerda = esquerda;
        this.direita = direita;
    }

    @Override
    public boolean satisfeitoPor(T candidato) {
        return esquerda.satisfeitoPor(candidato) || direita.satisfeitoPor(candidato);
    }
}
