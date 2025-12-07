package com.example.restaurant.restaurantService.service.menuItem;

import com.example.restaurant.restaurantService.dto.menuItem.MenuItemRequestDTO;
import com.example.restaurant.restaurantService.dto.menuItem.MenuItemResponseDTO;
import com.example.restaurant.restaurantService.exception.MenuItemNotFoundException;
import com.example.restaurant.restaurantService.exception.RestaurantNotFoundException;
import com.example.restaurant.restaurantService.mapper.MenuItemMapper;
import com.example.restaurant.restaurantService.model.MenuItem;
import com.example.restaurant.restaurantService.model.Restaurant;
import com.example.restaurant.restaurantService.reposiroty.MenuItemRepository;
import com.example.restaurant.restaurantService.reposiroty.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class MenuItemService {
    private final MenuItemRepository menuItemRepository;
    private final RestaurantRepository restaurantRepository;
    private final MenuItemMapper menuItemMapper;

    public MenuItemService(MenuItemRepository menuItemRepository,
                           RestaurantRepository restaurantRepository, MenuItemMapper menuItemMapper) {
        this.menuItemRepository = menuItemRepository;
        this.restaurantRepository = restaurantRepository;
        this.menuItemMapper = menuItemMapper;
    }

    public MenuItemResponseDTO addMenuItem(Long restaurantId, MenuItemRequestDTO menuItemRequestDTO) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new RestaurantNotFoundException("Restaurant not Found, Item can't be added !"));
        MenuItem menuItem = menuItemMapper.toEntity(restaurant,menuItemRequestDTO);
        return menuItemMapper.toResponseDTO(menuItemRepository.save(menuItem));
    }

    public List<MenuItemResponseDTO> addMenuItems(Long restaurantId, List<MenuItemRequestDTO> menuItemRequestDTOList) {
        List<MenuItemResponseDTO> addedMenuItems = new ArrayList<>();

        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new RestaurantNotFoundException("Restaurant not Found, Item can't be added !"));

        for(MenuItemRequestDTO menuItemRequestDTO : menuItemRequestDTOList) {
            MenuItem menuItem = menuItemMapper.toEntity(restaurant,menuItemRequestDTO);
            addedMenuItems.add(menuItemMapper.toResponseDTO(menuItemRepository.save(menuItem)));
        }
        return addedMenuItems;
    }

    @Transactional
    public MenuItemResponseDTO updateMenuItem(
            Long restaurantId,Long menuItemId,MenuItemRequestDTO menuItemRequestDTO) {
        MenuItem menuItem = menuItemRepository.findByMenuItemIdAndRestaurant_RestaurantId(
                menuItemId, restaurantId).orElseThrow(() ->
                new RestaurantNotFoundException("Item doesn't belong to this restaurant!"));

        menuItem.setItemName(menuItemRequestDTO.getItemName());
        menuItem.setItemQuantity(menuItemRequestDTO.getItemQuantity());
        menuItem.setItemPrice(menuItemRequestDTO.getItemPrice());

        menuItemRepository.save(menuItem);

        return menuItemMapper.toResponseDTO(menuItem);
    }

    public void removeMenuItem(Long restaurantId, Long menuItemId) {
        int rowsAffected = menuItemRepository
                .deleteByMenuItemIdAndRestaurant_RestaurantId(menuItemId,restaurantId);

        if(rowsAffected == 0) throw new MenuItemNotFoundException("Menu Item doesn't belong to this restaurant!");
    }

    public List<MenuItemResponseDTO> getMenuItems(Long restaurant_id) {
        List<MenuItem> menuItems = menuItemRepository.findByRestaurant_RestaurantId(restaurant_id);
        if(menuItems.isEmpty()) throw new MenuItemNotFoundException("No menu items present for this restaurant !");

        List<MenuItemResponseDTO> menuItemsDTO = new ArrayList<>();

        for(MenuItem menuItem : menuItems) {
            menuItemsDTO.add(menuItemMapper.toResponseDTO(menuItem));
        }

        return menuItemsDTO;
    }

    public MenuItemResponseDTO getMenuItem(Long restaurant_id, Long menuItem_id) {
        MenuItem menuItem = menuItemRepository.findByMenuItemIdAndRestaurant_RestaurantId(
                restaurant_id, menuItem_id).orElseThrow(
                        () -> new MenuItemNotFoundException("Menu Item Not found for this Restaurant !"));

        return menuItemMapper.toResponseDTO(menuItem);
    }


}