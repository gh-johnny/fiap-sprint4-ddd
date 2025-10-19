package com.dasa.keepinventory.domain.specifications.base;

public class NaoSpecification<T> implements Specification<T> {
    private final Specification<T> spec;

    public NaoSpecification(Specification<T> spec) {
        this.spec = spec;
    }

    @Override
    public boolean satisfeitoPor(T candidato) {
        return !spec.satisfeitoPor(candidato);
    }
}
