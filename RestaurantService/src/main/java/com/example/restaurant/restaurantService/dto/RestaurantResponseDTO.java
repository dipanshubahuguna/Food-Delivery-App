package com.example.restaurant.restaurantService.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RestaurantResponseDTO {
    public Long restaurantId;
    private String restaurantName;
    private String restaurantAddress;

}
