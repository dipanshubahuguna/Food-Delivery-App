package com.example.cart.cartService.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestDTO {
    private Long userId;
    private Long restaurantId;
    private Long menuItemId;
}
