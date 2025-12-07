package com.example.restaurant.restaurantService.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(RestaurantNotFoundException.class)
    public ResponseEntity<?> handleRestaurantNotFound(RestaurantNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(MenuItemNotFoundException.class)
    public ResponseEntity<?> handleMenuItemNotFound(MenuItemNotFoundException ex) {
        return ResponseEntity.status((HttpStatus.NOT_FOUND)).body(ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodValidation(MethodArgumentNotValidException ex) {
        Map<String,String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(err -> {
            errors.put(err.getField(),err.getDefaultMessage());
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }



}
