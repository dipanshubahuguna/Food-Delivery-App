package com.example.cart.cartService.dto.menuItem;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MenuItemDTO {
        private Long itemId;
        private String itemName;
        private Double itemQuantity;
        private Double itemPrice;
        private String restaurantName;
        private String restaurantAddress;
}
