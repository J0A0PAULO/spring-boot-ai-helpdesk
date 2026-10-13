package br.com.helpDesk.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFound.class)
    public ResponseEntity<String> notFound(NotFound notFound) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(notFound.getMessage());
    }

}
