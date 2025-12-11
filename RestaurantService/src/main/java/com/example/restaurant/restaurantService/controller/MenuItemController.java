package com.example.restaurant.restaurantService.controller;

import com.example.restaurant.restaurantService.dto.menuItem.MenuItemRequestDTO;
import com.example.restaurant.restaurantService.dto.menuItem.MenuItemResponseDTO;
import com.example.restaurant.restaurantService.service.menuItem.MenuItemService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path = "/menu-items")
public class MenuItemController {

    private final MenuItemService menuItemService;
    private static final Logger log = LoggerFactory.getLogger(MenuItemController.class);

    public MenuItemController(MenuItemService menuItemService) {
        this.menuItemService = menuItemService;
    }

    @PostMapping(path = "/add-menu-item/{restaurant_id}")
    public ResponseEntity<MenuItemResponseDTO> addMenuItem(
            @PathVariable @NotNull @Positive Long restaurant_id,
            @RequestBody @Valid MenuItemRequestDTO menuItemRequestDTO) {
        log.info("Received request to add menu item {} for restaurant with id {}",menuItemRequestDTO,restaurant_id);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                menuItemService.addMenuItem(restaurant_id, menuItemRequestDTO));
    }

    @PostMapping(path = "/add-menu-items/{restaurant_id}")
    public ResponseEntity<List<MenuItemResponseDTO>> addMenuItems(
            @PathVariable @NotNull @Positive Long restaurant_id,
            @RequestBody @Valid List<MenuItemRequestDTO> menuItemRequestDTOList) {
        log.info("Received request to add menu item for restaurant with id {}",restaurant_id);
        for(MenuItemRequestDTO menuItemRequestDTO : menuItemRequestDTOList){
            log.info("Menu item {}",menuItemRequestDTO);
        }
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(menuItemService.addMenuItems(restaurant_id, menuItemRequestDTOList));
    }
    // TODO : Check this, Throwing error!
    @DeleteMapping(path = "/delete-menu-item/{restaurant_id}/{menuItem_id}")
    public ResponseEntity<Void> removeMenuItemFromRestaurant(
            @PathVariable @NotNull @Positive Long restaurant_id,
            @PathVariable @NotNull @Positive Long menuItem_id) {
        log.info("Received request to remove menu item with id {} for restaurant with id {}",menuItem_id,restaurant_id);
        menuItemService.removeMenuItem(restaurant_id,menuItem_id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(path = "/update-menu-item/{restaurant_id}/menu-item/{menuItem_id}")
    public ResponseEntity<MenuItemResponseDTO> updateMenuItemForRestaurant(
            @PathVariable @NotNull @Positive Long restaurant_id,
            @PathVariable @NotNull @Positive Long menuItem_id,
            @RequestBody @Valid MenuItemRequestDTO menuItemRequestDTO) {
        log.info("Received request to update menu item with id {} for restaurant with id {} to {}",menuItem_id, restaurant_id, menuItemRequestDTO);
        return ResponseEntity.ok(menuItemService.updateMenuItem(restaurant_id
                , menuItem_id, menuItemRequestDTO));
    }

    @GetMapping(path = "/restaurants/{restaurant_id}/menu-item")
    public ResponseEntity<List<MenuItemResponseDTO>> getMenuItemsForRestaurant(
            @PathVariable @NotNull @Positive Long restaurant_id) {
        log.info("Received request to fetch menu items of restaurant with id {}",restaurant_id);
        return ResponseEntity.ok(menuItemService.getMenuItems(restaurant_id));
    }

    @GetMapping(path = "/restaurant/{restaurant_id}/menu-item/{menuItem_id}")
    public MenuItemResponseDTO getMenuItem(
            @PathVariable @NotNull @Positive Long restaurant_id
            ,@PathVariable @NotNull @Positive Long menuItem_id) {
        log.info("Received request to fetch menu item detail with id {} for restaurant with id {}",menuItem_id, restaurant_id);
        return ResponseEntity.ok(menuItemService.getMenuItem(restaurant_id,menuItem_id)).getBody();
    }
}
