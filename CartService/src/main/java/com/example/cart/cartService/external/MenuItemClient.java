package com.example.cart.cartService.external;


import com.example.cart.cartService.dto.menuItem.MenuItemDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "MenuItem-Service",url = "http://localhost:8081", path = "/menu-items")
public interface MenuItemClient {
    @GetMapping(path = "/restaurant/{restaurant_id}/menu-item/{menu_item_id}")
    public MenuItemDTO getMenuItem(@PathVariable("restaurant_id") Long restaurantId,
            @PathVariable("menu_item_id") Long menuItemId);
}
