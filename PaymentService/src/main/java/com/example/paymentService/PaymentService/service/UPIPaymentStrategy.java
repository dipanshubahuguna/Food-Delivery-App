package com.example.paymentService.PaymentService.service;


import com.example.paymentService.PaymentService.dto.PaymentRequestDTO;
import com.example.paymentService.PaymentService.enums.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Service("UPI")
@NoArgsConstructor
public class UPIPaymentStrategy implements PaymentStrategy{

    public PaymentStatus pay(PaymentRequestDTO paymentRequestDTO) {
        System.out.println("Processing payment for : " + paymentRequestDTO.getAmount() + " rupees ");
        return paymentProbability() ? PaymentStatus.SUCCESS : PaymentStatus.FAILED;
    }
}
