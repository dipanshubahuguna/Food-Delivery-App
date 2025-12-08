package com.example.orderService.OrderService.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cart {
    private Long cartId;
    private Long userId;
    private Double totalAmount;
    private List<CartItem> cartItems;
}

