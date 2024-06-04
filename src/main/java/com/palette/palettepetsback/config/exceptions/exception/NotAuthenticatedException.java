package com.palette.palettepetsback.config.exceptions.exception;

public class NotAuthenticatedException extends RuntimeException {

    public NotAuthenticatedException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotAuthenticatedException(String message) {
        super(message);
    }

    public NotAuthenticatedException(Throwable cause) {
        super(cause);
    }
}
