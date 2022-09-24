package br.inatel.thisismeapi.services.exceptions;

public class TokenInvalidException extends RuntimeException{
    public TokenInvalidException(String message) {
        super(message);
    }
}
