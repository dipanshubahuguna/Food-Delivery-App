package com.example.restaurant.restaurantService.dto.restaurant;

import com.example.restaurant.restaurantService.dto.menuItem.MenuItemResponseDTO;
import com.example.restaurant.restaurantService.dto.menuItem.MenuItemWithoutRestaurantDTO;
import com.example.restaurant.restaurantService.model.MenuItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantResponseDTO {
    public Long restaurantId;
    private String restaurantName;
    private String restaurantAddress;
    public List<MenuItemWithoutRestaurantDTO> menuItemList;
}
