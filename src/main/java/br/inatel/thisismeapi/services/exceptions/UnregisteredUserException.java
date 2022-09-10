package br.inatel.thisismeapi.services.exceptions;

public class UnregisteredUserException extends RuntimeException {
    public UnregisteredUserException(String message) {
        super(message);
    }
}
