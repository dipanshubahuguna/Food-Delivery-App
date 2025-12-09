package com.example.paymentService.PaymentService.exception;

public class PaymentStrategyNotFoundException extends RuntimeException{
    public PaymentStrategyNotFoundException(String message) {
        super(message);
    }
}
