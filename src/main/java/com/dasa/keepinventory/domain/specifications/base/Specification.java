package com.dasa.keepinventory.domain.specifications.base;

public interface Specification<T> {
    boolean satisfeitoPor(T candidato);
    
    default Specification<T> e(Specification<T> outra) {
        return new ESpecification<>(this, outra);
    }
    
    default Specification<T> ou(Specification<T> outra) {
        return new OuSpecification<>(this, outra);
    }
    
    default Specification<T> nao() {
        return new NaoSpecification<>(this);
    }
}
