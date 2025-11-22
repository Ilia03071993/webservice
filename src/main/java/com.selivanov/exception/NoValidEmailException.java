package com.selivanov.exception;

public class NoValidEmailException extends RuntimeException {
    public NoValidEmailException(String message) {
        super(message);
    }
}
