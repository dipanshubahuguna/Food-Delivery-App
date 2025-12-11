package com.example.orderService.OrderService.dto.payment;
import com.example.orderService.OrderService.enums.PaymentStatus;
import com.example.orderService.OrderService.enums.payment.PaymentMode;
import lombok.AllArgsConstructor;
import lombok.Getter;
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
