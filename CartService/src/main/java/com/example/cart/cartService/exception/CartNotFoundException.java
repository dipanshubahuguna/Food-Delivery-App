package com.example.cart.cartService.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;

public class CartNotFoundException extends RuntimeException{
    public CartNotFoundException(String message){
        super(message);
    }
}
