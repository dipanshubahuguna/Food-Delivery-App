package com.example.restaurant.restaurantService.mapper;

import com.example.restaurant.restaurantService.dto.menuItem.MenuItemRequestDTO;
import com.example.restaurant.restaurantService.dto.menuItem.MenuItemResponseDTO;
import com.example.restaurant.restaurantService.dto.menuItem.MenuItemWithoutRestaurantDTO;
import com.example.restaurant.restaurantService.dto.restaurant.RestaurantRequestDTO;
import com.example.restaurant.restaurantService.dto.restaurant.RestaurantResponseDTO;
import com.example.restaurant.restaurantService.model.MenuItem;
import com.example.restaurant.restaurantService.model.Restaurant;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public final class RestaurantMapper {
    public RestaurantResponseDTO toResponseDTO(Restaurant restaurant) {
        RestaurantResponseDTO dto = new RestaurantResponseDTO();
        dto.setRestaurantId(restaurant.getRestaurantId());
        dto.setRestaurantName(restaurant.getRestaurantName());
        dto.setRestaurantAddress(restaurant.getRestaurantAddress());
        List<MenuItemWithoutRestaurantDTO> menuItemDTOList = new ArrayList<>();

        for(MenuItem menuItem : restaurant.getMenuItemList()) {
            MenuItemWithoutRestaurantDTO menuItemDTO = new MenuItemWithoutRestaurantDTO(
                    menuItem.getMenuItemId(),
                    menuItem.getItemName(),
                    menuItem.getItemQuantity(),
                    menuItem.getItemPrice());
            menuItemDTOList.add(menuItemDTO);
        }
        dto.setMenuItemList(menuItemDTOList);
        return dto;
    }

    public Restaurant toEntity(RestaurantRequestDTO restaurantRequestDTO) {
        Restaurant restaurant = new Restaurant();
        restaurant.setRestaurantName(restaurantRequestDTO.getRestaurantName());
        restaurant.setRestaurantAddress(restaurantRequestDTO.getRestaurantAddress());
        return restaurant;
    }
}
