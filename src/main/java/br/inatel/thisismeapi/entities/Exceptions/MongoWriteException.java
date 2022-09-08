package br.inatel.thisismeapi.entities.Exceptions;

public class MongoWriteException extends RuntimeException {
    public MongoWriteException(String msg) {
        super(msg);
    }
}
