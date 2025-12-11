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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger log = LoggerFactory.getLogger(MenuItemService.class);

    public MenuItemService(MenuItemRepository menuItemRepository,
                           RestaurantRepository restaurantRepository, MenuItemMapper menuItemMapper) {
        this.menuItemRepository = menuItemRepository;
        this.restaurantRepository = restaurantRepository;
        this.menuItemMapper = menuItemMapper;
    }

    public MenuItemResponseDTO addMenuItem(Long restaurantId, MenuItemRequestDTO menuItemRequestDTO) {
        log.debug("Finding restaurant details");
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new RestaurantNotFoundException("Restaurant not Found, Item can't be added !"));
        log.debug("Restaurant present, adding menu item");
        MenuItem menuItem = menuItemMapper.toEntity(restaurant,menuItemRequestDTO);
        return menuItemMapper.toResponseDTO(menuItemRepository.save(menuItem));
    }

    public List<MenuItemResponseDTO> addMenuItems(Long restaurantId, List<MenuItemRequestDTO> menuItemRequestDTOList) {
        List<MenuItemResponseDTO> addedMenuItems = new ArrayList<>();
        log.debug("Finding restaurant details");
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new RestaurantNotFoundException("Restaurant not Found, Item can't be added !"));
        log.debug("Restaurant present, adding menu items");
        for(MenuItemRequestDTO menuItemRequestDTO : menuItemRequestDTOList) {
            MenuItem menuItem = menuItemMapper.toEntity(restaurant,menuItemRequestDTO);
            addedMenuItems.add(menuItemMapper.toResponseDTO(menuItemRepository.save(menuItem)));
        }
        return addedMenuItems;
    }

    @Transactional
    public MenuItemResponseDTO updateMenuItem(
            Long restaurantId,Long menuItemId,
            MenuItemRequestDTO menuItemRequestDTO)
    {
        log.debug("Finding menu item for restaurant");
        MenuItem menuItem = menuItemRepository.findByMenuItemIdAndRestaurant_RestaurantId(
                menuItemId, restaurantId).orElseThrow(() ->
                new RestaurantNotFoundException("Item doesn't belong to this restaurant!"));
        log.debug("Menu item found");
        menuItem.setItemName(menuItemRequestDTO.getItemName());
        menuItem.setItemQuantity(menuItemRequestDTO.getItemQuantity());
        menuItem.setItemPrice(menuItemRequestDTO.getItemPrice());
        log.debug("saving it into the database");
        menuItemRepository.save(menuItem);
        return menuItemMapper.toResponseDTO(menuItem);
    }

    public void removeMenuItem(Long restaurantId, Long menuItemId) {
        log.debug("Deleting menu item of a restaurant");
        int rowsAffected = menuItemRepository
                .deleteByMenuItemIdAndRestaurant_RestaurantId(menuItemId,restaurantId);
        if(rowsAffected == 0) throw new MenuItemNotFoundException("Menu Item doesn't belong to this restaurant!");
        log.debug("Menu item deleted");
    }

    public List<MenuItemResponseDTO> getMenuItems(Long restaurant_id) {
        log.debug("Finding menu items for the restaurant");
        List<MenuItem> menuItems = menuItemRepository.findByRestaurant_RestaurantId(restaurant_id);
        if(menuItems.isEmpty()) throw new MenuItemNotFoundException("No menu items present for this restaurant !");
        log.debug("menu items found");

        List<MenuItemResponseDTO> menuItemsDTO = new ArrayList<>();
        for(MenuItem menuItem : menuItems) {
            menuItemsDTO.add(menuItemMapper.toResponseDTO(menuItem));
        }
        return menuItemsDTO;
    }

    public MenuItemResponseDTO getMenuItem(Long restaurant_id, Long menuItem_id) {
        log.debug("Finding menu items for the restaurant");
        MenuItem menuItem = menuItemRepository.findByMenuItemIdAndRestaurant_RestaurantId(
                menuItem_id,restaurant_id).orElseThrow(
                        () -> new MenuItemNotFoundException("Menu Item Not found for this Restaurant !"));
        log.debug("Menu item found");
        return menuItemMapper.toResponseDTO(menuItem);
    }


}