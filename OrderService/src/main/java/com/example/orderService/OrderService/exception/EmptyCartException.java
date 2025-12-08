package com.example.orderService.OrderService.exception;

public class EmptyCartException extends RuntimeException{

    public EmptyCartException(String message) {
        super(message);
    }
}
