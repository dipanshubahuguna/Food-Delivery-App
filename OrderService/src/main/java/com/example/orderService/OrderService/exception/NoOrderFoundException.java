package com.example.orderService.OrderService.exception;

public class NoOrderFoundException extends RuntimeException{

    public NoOrderFoundException(String message) {
        super(message);
    }
}
