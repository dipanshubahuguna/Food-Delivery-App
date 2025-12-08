package com.example.cart.cartService.exception;

public class CartItemNotFoundException extends RuntimeException{
    public CartItemNotFoundException(String message){
        super(message);
    }
}
