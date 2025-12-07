package com.example.restaurant.restaurantService.dto.menuItem;

import jakarta.validation.constraints.*;
import lombok.*;


@Data
@AllArgsConstructor
public class MenuItemResponseDTO {
    private Long itemId;
    private String itemName;

    private Double itemQuantity;

    private Double itemPrice;

    private String restaurantName;

    private String restaurantAddress;
}
