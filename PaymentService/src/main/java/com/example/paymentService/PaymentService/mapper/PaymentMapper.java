package com.example.paymentService.PaymentService.mapper;

import com.example.paymentService.PaymentService.dto.PaymentRequestDTO;
import com.example.paymentService.PaymentService.dto.PaymentResponseDTO;
import com.example.paymentService.PaymentService.enums.PaymentMode;
import com.example.paymentService.PaymentService.enums.PaymentStatus;
import com.example.paymentService.PaymentService.model.Payment;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class PaymentMapper {
    public PaymentResponseDTO toResponseDTO(Payment payment) {
        return new PaymentResponseDTO(
                payment.getPaymentId(),
                payment.getOrderId(),
                payment.getUserId(),
                payment.getAmount(),
                payment.getPaymentMode(),
                payment.getPaymentStatus(),
                payment.getPaymentTime()
        );
    }

    public Payment toEntity(PaymentRequestDTO paymentRequestDTO, PaymentStatus paymentStatus) {
        Payment payment = new Payment();
        payment.setOrderId(paymentRequestDTO.getOrderId());
        payment.setUserId(paymentRequestDTO.getUserId());
        payment.setPaymentMode(paymentRequestDTO.getPaymentMode());
        payment.setPaymentStatus(paymentStatus);
        payment.setAmount(paymentRequestDTO.getAmount());
        payment.setPaymentTime(LocalDateTime.now());

        return payment;
    }
}
