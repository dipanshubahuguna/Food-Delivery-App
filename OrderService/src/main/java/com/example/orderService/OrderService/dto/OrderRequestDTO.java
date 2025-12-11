package com.example.orderService.OrderService.dto;

import com.example.orderService.OrderService.enums.payment.PaymentMode;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderRequestDTO {
    @NotNull
    private Long userId;
    @NotNull
    private PaymentMode paymentMode;
}
