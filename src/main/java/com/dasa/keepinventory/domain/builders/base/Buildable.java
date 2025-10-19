package com.dasa.keepinventory.domain.builders.base;

public interface Buildable<T> {
    static <B extends Builder<T>, T> B builder() {
        throw new UnsupportedOperationException("Deve ser implementado pela entidade");
    }
}
