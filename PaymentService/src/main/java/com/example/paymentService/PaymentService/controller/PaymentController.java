package com.example.paymentService.PaymentService.controller;

import com.example.paymentService.PaymentService.dto.PaymentRequestDTO;
import com.example.paymentService.PaymentService.dto.PaymentResponseDTO;
import com.example.paymentService.PaymentService.service.PaymentService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/payment")
@AllArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;
    private static final Logger log = LoggerFactory.getLogger(PaymentController.class);
    @GetMapping(path = "/sts/{order_id}")
    public ResponseEntity<PaymentResponseDTO> getPayment(
            @PathVariable("order_id") @NotNull @Positive Long order_id) {
        log.info("Received get payment request for Order id {}",order_id);
        return ResponseEntity.ok(paymentService.getPaymentDetails(order_id));
    }

    @PostMapping(path = "/initiate")
    public ResponseEntity<PaymentResponseDTO> initiatePayment(
            @RequestBody @Valid PaymentRequestDTO paymentRequestDTO) {
        log.info("Received request to initiate payment");
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(paymentService.processPayment(paymentRequestDTO));
    }
}
