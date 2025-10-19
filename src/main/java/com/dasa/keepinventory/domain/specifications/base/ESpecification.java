package com.dasa.keepinventory.domain.specifications.base;

public class ESpecification<T> implements Specification<T> {
    private final Specification<T> esquerda;
    private final Specification<T> direita;

    public ESpecification(Specification<T> esquerda, Specification<T> direita) {
        this.esquerda = esquerda;
        this.direita = direita;
    }

    @Override
    public boolean satisfeitoPor(T candidato) {
        return esquerda.satisfeitoPor(candidato) && direita.satisfeitoPor(candidato);
    }
}
