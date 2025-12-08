package com.example.orderService.OrderService.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartItem {
    private Long cartItemId;
    private Long menuItemId;
    private Long restaurantId;
    private String itemName;
    private Long itemQuantity;
    private Double itemPrice;
}
