package com.example.paymentService.PaymentService.dto;

import com.example.paymentService.PaymentService.enums.PaymentMode;
import com.example.paymentService.PaymentService.enums.PaymentStatus;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
public class PaymentResponseDTO {
    private Long paymentId;
    private Long orderId;
    private Long userId;
    private Double amount;
    private PaymentMode paymentMode;
    private PaymentStatus paymentStatus;
    private LocalDateTime paymentTime;
}
