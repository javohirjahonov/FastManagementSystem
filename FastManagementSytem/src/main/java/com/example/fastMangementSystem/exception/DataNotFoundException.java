package com.example.fastMangementSystem.exception;

public class DataNotFoundException extends RuntimeException {
    public DataNotFoundException(String userNotFound) {
        super(userNotFound);
    }
}
