package br.inatel.thisismeapi.controllers.exceptions;

import javax.validation.ValidationException;

public class ConstraintViolationException extends ValidationException {
    public ConstraintViolationException(String message) {
        super(message);
    }
}
