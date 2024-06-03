package com.palette.palettepetsback.config.exceptions.exception;

public class BasicLoginIOException extends RuntimeException {

    public BasicLoginIOException(String message, Throwable cause) {
        super(message, cause);
    }

    public BasicLoginIOException(String message) {
        super(message);
    }

    public BasicLoginIOException(Throwable cause) {
        super(cause);
    }
}
