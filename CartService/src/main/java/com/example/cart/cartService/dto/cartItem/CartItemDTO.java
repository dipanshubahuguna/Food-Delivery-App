package com.example.cart.cartService.dto.cartItem;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartItemDTO {

    private Long cartItemId;
    private Long menuItemId;
    private Long restaurantId;
    private String itemName;
    private Long itemQuantity;
    private Double itemPrice;

}
