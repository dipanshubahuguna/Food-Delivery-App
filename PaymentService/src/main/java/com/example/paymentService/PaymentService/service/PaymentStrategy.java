package com.example.paymentService.PaymentService.service;

import com.example.paymentService.PaymentService.dto.PaymentRequestDTO;
import com.example.paymentService.PaymentService.enums.PaymentStatus;

public interface PaymentStrategy {
    public PaymentStatus pay(PaymentRequestDTO paymentRequestDTO);
    default Boolean paymentProbability() {
        int min = 10, max = 50;
        int probability = min + (int)(Math.random()*(max - min) + 1);

        try{
            Thread.sleep(1000);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return (probability % 2 == 0) ? true : false;
    }
}
