package com.dasa.keepinventory.shared.exceptions;

public class EntityNotFoundException extends DomainException {
    public EntityNotFoundException(String entityName, Long id) {
        super(String.format("%s com ID %d não encontrado", entityName, id));
    }

    public EntityNotFoundException(String message) {
        super(message);
    }
}
