package com.example.restaurant.restaurantService.dto.menuItem;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuItemWithoutRestaurantDTO {
    private Long itemId;
    private String itemName;
    private Double itemQuantity;
    private Double itemPrice;
}
