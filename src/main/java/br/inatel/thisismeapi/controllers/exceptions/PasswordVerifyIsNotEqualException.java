package br.inatel.thisismeapi.controllers.exceptions;

public class PasswordVerifyIsNotEqualException extends RuntimeException {
    public PasswordVerifyIsNotEqualException(String message) {
        super(message);
    }
}
