package com.example.orderService.OrderService.dto.cart;

import lombok.Getter;

import java.util.List;

@Getter
public class CartResponseDTO {
    private Long cartId;
    private Long userId;
    private Double totalAmount;
    private List<CartItemDTO> cartItems;
}
