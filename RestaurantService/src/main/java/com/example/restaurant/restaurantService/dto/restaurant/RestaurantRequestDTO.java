package com.example.restaurant.restaurantService.dto.restaurant;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;

@Getter
public class RestaurantRequestDTO {
    @NotBlank(message = "Name cannot be blank")
    private String restaurantName;
    @NotBlank(message = "Address cannot be blank")
    private String restaurantAddress;

    @Override
    public String toString() {
        return "Restaurant Name : " + restaurantName + " Address : " + restaurantAddress;
    }
}
