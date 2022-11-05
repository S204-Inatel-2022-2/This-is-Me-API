package br.inatel.thisismeapi.exceptions;

public class ErrorOnCreateException extends RuntimeException {

    public ErrorOnCreateException(String message) {
        super(message);
    }
}
