package com.example.restaurant.restaurantService.reposiroty;


import com.example.restaurant.restaurantService.model.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Repository
public interface MenuItemRepository extends JpaRepository<MenuItem,Long> {
    @Transactional
    public int deleteByMenuItemIdAndRestaurant_RestaurantId(Long menuItemId,Long restaurantId);
    public Optional<MenuItem> findByMenuItemIdAndRestaurant_RestaurantId(Long menuItemId, Long restaurantId);
    public List<MenuItem> findByRestaurant_RestaurantId(Long restaurant_id);

}
