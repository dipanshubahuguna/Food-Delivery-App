package com.example.orderService.OrderService.controller;

import com.example.orderService.OrderService.dto.OrderDTO;
import com.example.orderService.OrderService.service.OrderService;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/order")
@AllArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping(path = "/place/{user_id}")
    public ResponseEntity<OrderDTO> placeOrder(
            @PathVariable("user_id") @NotNull @Positive Long userId) {
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.createOrder(userId));
    }

    @GetMapping(path = "/order/{order_id}")
    public ResponseEntity<OrderDTO> getOrder(
            @PathVariable("order_id") @NotNull @Positive Long orderId ){
        return ResponseEntity.ok(orderService.getOrderById(orderId));
    }

    @GetMapping(path = "/orders/{user_id}")
    public ResponseEntity<List<OrderDTO>> getOrdersOfUser(
            @PathVariable("user_id") @NotNull @Positive Long userId ){
        return ResponseEntity.ok(orderService.getOrdersByUserId(userId));
    }

}
