package com.example.paymentService.PaymentService.dto;

import com.example.paymentService.PaymentService.enums.PaymentMode;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class PaymentRequestDTO {
    @NotNull @Positive
    private Long userId;
    @NotNull @Positive
    private Long orderId;
    @NotNull @Positive
    private Double amount;
    @NotNull
    private PaymentMode paymentMode;
}
