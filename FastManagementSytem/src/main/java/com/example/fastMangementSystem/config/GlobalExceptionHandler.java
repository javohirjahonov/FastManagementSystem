package com.example.fastMangementSystem.config;

import com.example.fastMangementSystem.exception.AuthenticationFailedException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = AuthenticationFailedException.class)
    public ResponseEntity<String> authenticationFailedException(
            AuthenticationFailedException e
    )
    {
        return ResponseEntity.status(401).body(e.getMessage());
    }
}
