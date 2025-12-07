package com.example.restaurant.restaurantService.mapper;

import com.example.restaurant.restaurantService.dto.menuItem.MenuItemRequestDTO;
import com.example.restaurant.restaurantService.dto.menuItem.MenuItemResponseDTO;
import com.example.restaurant.restaurantService.model.MenuItem;
import com.example.restaurant.restaurantService.model.Restaurant;
import org.springframework.stereotype.Component;

@Component
public final class MenuItemMapper {
    public MenuItem toEntity(Restaurant restaurant, MenuItemRequestDTO menuItemRequestDTO) {
        MenuItem menuItem = new MenuItem();
        menuItem.setItemName(menuItemRequestDTO.getItemName());
        menuItem.setItemQuantity(menuItemRequestDTO.getItemQuantity());
        menuItem.setItemPrice(menuItemRequestDTO.getItemPrice());
        menuItem.setRestaurant(restaurant);
        return menuItem;
    }

    public MenuItemResponseDTO toResponseDTO(MenuItem menuItem) {
        return new MenuItemResponseDTO(menuItem.getMenuItemId(), menuItem.getItemName(),
                menuItem.getItemQuantity(), menuItem.getItemPrice(),
                menuItem.getRestaurant().getRestaurantName(),
                menuItem.getRestaurant().getRestaurantAddress());
    }
}
