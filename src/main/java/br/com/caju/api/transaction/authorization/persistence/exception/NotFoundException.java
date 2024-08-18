package br.com.caju.api.transaction.authorization.persistence.exception;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}
