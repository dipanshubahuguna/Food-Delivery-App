package com.example.orderService.OrderService.external;


import com.example.orderService.OrderService.dto.cart.CartResponseDTO;
import com.example.orderService.OrderService.dto.payment.PaymentRequestDTO;
import com.example.orderService.OrderService.dto.payment.PaymentResponseDTO;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "Payment-Service",
            path = "/payment")
public interface PaymentClient {
    @PostMapping(path = "/initiate")
    public PaymentResponseDTO initiatePayment(@RequestBody PaymentRequestDTO paymentRequestDTO);
}
