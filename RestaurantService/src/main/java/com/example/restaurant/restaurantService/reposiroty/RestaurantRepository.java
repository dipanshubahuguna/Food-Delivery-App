package com.example.restaurant.restaurantService.reposiroty;

import com.example.restaurant.restaurantService.model.RestaurantEntity;
import com.example.restaurant.restaurantService.service.RestaurantService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface RestaurantRepository extends JpaRepository<RestaurantEntity,Long> {
    RestaurantEntity findByRestaurantName(String name);
    List<RestaurantEntity> findByRestaurantAddress(String address);
}
