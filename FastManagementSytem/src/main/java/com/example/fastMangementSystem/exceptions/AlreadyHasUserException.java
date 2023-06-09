package com.example.fastMangementSystem.exceptions;

public class AlreadyHasUserException extends RuntimeException {
    public AlreadyHasUserException(String message) {
        super(message);
    }
}
