package com.example.restaurant.restaurantService.dto.menuItem;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@AllArgsConstructor
public class MenuItemRequestDTO {

    @NotBlank(message = "Item name cannot be blank!")
    private String itemName;

    @NotNull(message = "Restaurant Id should not be null!")
    @DecimalMin(value = "0.1", message = "item quantity should be more than 0!")
    private Double itemQuantity;

    @NotNull(message = "Restaurant Id should not be null!")
    @DecimalMin(value = "1.0", message = "item price should be more than 1!")
    private Double itemPrice;
}
