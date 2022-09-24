package br.inatel.thisismeapi.services.exceptions;

public class TokenExpiredException extends RuntimeException{

    public TokenExpiredException(String message) {
        super(message);
    }
}
