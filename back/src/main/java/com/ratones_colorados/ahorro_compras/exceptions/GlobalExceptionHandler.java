package com.ratones_colorados.ahorro_compras.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice // Indica que esta clase manejará excepciones de forma global para todos los controladores en la aplicación.
public class GlobalExceptionHandler {

    // Maneja las excepciones de tipo ResponseStatusException.
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<String> handleResponseStatusException(ResponseStatusException ex) {
        // Retorna una ResponseEntity con la razón de la excepción y el código de estado HTTP correspondiente.
        return new ResponseEntity<>(ex.getReason(), ex.getStatusCode());
    }

    // Maneja cualquier otra excepción genérica que no haya sido manejada por otros métodos.
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception ex) {
        // Retorna una ResponseEntity con el mensaje de la excepción y el estado HTTP 500 (Error Interno del Servidor).
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}



