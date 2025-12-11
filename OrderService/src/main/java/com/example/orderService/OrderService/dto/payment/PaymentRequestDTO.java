package com.example.orderService.OrderService.dto.payment;

import com.example.orderService.OrderService.enums.payment.PaymentMode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class PaymentRequestDTO {
    private Long userId;
    private Long orderId;
    private Double amount;
    private PaymentMode paymentMode;
}

