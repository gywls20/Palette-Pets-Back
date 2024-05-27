package com.palette.palettepetsback.config.exceptions;

public class NoSuchPetException extends RuntimeException {

    public NoSuchPetException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSuchPetException(String message) {
        super(message);
    }

    public NoSuchPetException(Throwable cause) {
        super(cause);
    }
}
