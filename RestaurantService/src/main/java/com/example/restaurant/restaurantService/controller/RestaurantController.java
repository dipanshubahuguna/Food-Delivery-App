package com.example.restaurant.restaurantService.controller;


import java.util.*;

import com.example.restaurant.restaurantService.dto.restaurant.RestaurantRequestDTO;
import com.example.restaurant.restaurantService.dto.restaurant.RestaurantResponseDTO;
import com.example.restaurant.restaurantService.service.restaurant.RestaurantService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/restaurant")
public class RestaurantController {
    private final RestaurantService restaurantService;
    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @PostMapping(path = "/add-restaurant")
    public ResponseEntity<RestaurantResponseDTO> addRestaurant(
            @RequestBody @Valid RestaurantRequestDTO restaurantRequestDTO){
        RestaurantResponseDTO addedRestaurant = restaurantService.addRestaurant(restaurantRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedRestaurant);
    }

    @DeleteMapping(path = "/delete-restaurant/{restaurant_id}")
    public ResponseEntity<Void> removeRestaurant(@PathVariable @Positive @NotNull Long restaurant_id) {
        restaurantService.removeRestaurant(restaurant_id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(path = "/get-restaurants/{address}")
    public ResponseEntity<List<RestaurantResponseDTO>> getRestaurantsList(
            @PathVariable @NotNull String address) {
        return ResponseEntity.ok(restaurantService.getRestaurantsList(address));
    }

    @GetMapping(path = "/get-restaurant/{restaurant_id}")
    public ResponseEntity<RestaurantResponseDTO> getRestaurant(
            @PathVariable @Positive @NotNull Long restaurant_id) {
        return ResponseEntity.ok(restaurantService.getRestaurant(restaurant_id));
    }

}
