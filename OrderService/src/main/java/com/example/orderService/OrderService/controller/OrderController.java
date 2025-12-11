package com.example.orderService.OrderService.controller;

import com.example.orderService.OrderService.dto.OrderDTO;
import com.example.orderService.OrderService.dto.OrderRequestDTO;
import com.example.orderService.OrderService.enums.payment.PaymentMode;
import com.example.orderService.OrderService.service.OrderService;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/order")
@AllArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private static final Logger log = LoggerFactory.getLogger(OrderController.class);
    @PostMapping(path = "/place")
    public ResponseEntity<OrderDTO> placeOrder(
            @RequestBody OrderRequestDTO orderRequestDTO) {
        log.info("Received placed order request");
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(orderService.createOrder(orderRequestDTO.getUserId(),orderRequestDTO.getPaymentMode()));
    }

    @GetMapping(path = "/order/{order_id}")
    public ResponseEntity<OrderDTO> getOrder(
            @PathVariable("order_id") @NotNull @Positive Long orderId ){
        log.info("Received get order request");
        return ResponseEntity.ok(orderService.getOrderById(orderId));
    }

    @GetMapping(path = "/orders/{user_id}")
    public ResponseEntity<List<OrderDTO>> getOrdersOfUser(
            @PathVariable("user_id") @NotNull @Positive Long userId ){
        log.info("Received request to fetch all the orders for a user with id {}",userId);
        return ResponseEntity.ok(orderService.getOrdersByUserId(userId));
    }

}
