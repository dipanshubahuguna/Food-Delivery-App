package com.example.paymentService.PaymentService.service;


import com.example.paymentService.PaymentService.dto.PaymentRequestDTO;
import com.example.paymentService.PaymentService.enums.PaymentStatus;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Service("CREDIT_CARD")
@NoArgsConstructor
public class CreditCardPaymentStrategy implements PaymentStrategy{

    public PaymentStatus pay(PaymentRequestDTO paymentRequestDTO) {
        System.out.println("Processing payment for : " + paymentRequestDTO.getAmount() + " rupees ");
        return paymentProbability() ? PaymentStatus.SUCCESS : PaymentStatus.FAILED;
    }
}
