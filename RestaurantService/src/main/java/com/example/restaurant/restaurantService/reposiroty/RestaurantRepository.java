package com.example.restaurant.restaurantService.reposiroty;

import com.example.restaurant.restaurantService.model.Restaurant;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant,Long> {
    Restaurant findByRestaurantName(String name);
    List<Restaurant> findByRestaurantAddress(String address);
}
