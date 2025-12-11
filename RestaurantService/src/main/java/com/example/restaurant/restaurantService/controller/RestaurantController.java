package com.example.restaurant.restaurantService.controller;


import java.util.*;

import com.example.restaurant.restaurantService.dto.restaurant.RestaurantRequestDTO;
import com.example.restaurant.restaurantService.dto.restaurant.RestaurantResponseDTO;
import com.example.restaurant.restaurantService.service.restaurant.RestaurantService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/restaurant")
public class RestaurantController {
    private final RestaurantService restaurantService;
    private static final Logger log = LoggerFactory.getLogger(RestaurantController.class);
    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @PostMapping(path = "/add-restaurant")
    public ResponseEntity<RestaurantResponseDTO> addRestaurant(
            @RequestBody @Valid RestaurantRequestDTO restaurantRequestDTO){
        log.info("Received request to add restaurant {} ",restaurantRequestDTO);
        RestaurantResponseDTO addedRestaurant = restaurantService.addRestaurant(restaurantRequestDTO);
        log.info("Restaurant created with id {}",addedRestaurant.getRestaurantId());
        return ResponseEntity.status(HttpStatus.CREATED).body(addedRestaurant);
    }

    @DeleteMapping(path = "/delete-restaurant/{restaurant_id}")
    public ResponseEntity<Void> removeRestaurant(@PathVariable @Positive @NotNull Long restaurant_id) {
        log.info("Received request to remove restaurant with restaurant id {}",restaurant_id);
        restaurantService.removeRestaurant(restaurant_id);
        log.info("Restaurant removed");
        return ResponseEntity.noContent().build();
    }

    @GetMapping(path = "/get-restaurants/{address}")
    public ResponseEntity<List<RestaurantResponseDTO>> getRestaurantsList(
            @PathVariable @NotNull String address) {
        log.info("Received request to get all restaurants in {} area",address);
        return ResponseEntity.ok(restaurantService.getRestaurantsList(address));
    }

    @GetMapping(path = "/get-restaurant/{restaurant_id}")
    public ResponseEntity<RestaurantResponseDTO> getRestaurant(
            @PathVariable @Positive @NotNull Long restaurant_id) {
        log.info("Received request to get restaurant details with restaurant id {}",restaurant_id);
        return ResponseEntity.ok(restaurantService.getRestaurant(restaurant_id));
    }

}
