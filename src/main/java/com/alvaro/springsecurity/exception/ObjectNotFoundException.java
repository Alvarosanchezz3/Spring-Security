package com.alvaro.springsecurity.exception;

// Excepción personalizada para objeto no encontrado
public class ObjectNotFoundException extends RuntimeException {

    public ObjectNotFoundException() {}

    public ObjectNotFoundException(String message) {
        super(message);
    }

    public ObjectNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
