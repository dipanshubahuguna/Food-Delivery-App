package com.example.orderService.OrderService.dto.cart;

import lombok.Getter;

@Getter
public class CartItemDTO {
    private Long cartItemId;
    private Long menuItemId;
    private Long restaurantId;
    private String itemName;
    private Double itemQuantity;
    private Double itemPrice;
}