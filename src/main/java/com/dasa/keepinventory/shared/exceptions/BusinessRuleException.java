package com.dasa.keepinventory.shared.exceptions;

public class BusinessRuleException extends DomainException {
    public BusinessRuleException(String message) {
        super(message);
    }

    public BusinessRuleException(String message, Throwable cause) {
        super(message, cause);
    }
}
