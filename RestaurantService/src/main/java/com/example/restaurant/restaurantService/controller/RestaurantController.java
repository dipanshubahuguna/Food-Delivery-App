package com.example.restaurant.restaurantService.controller;


import java.util.*;
import com.example.restaurant.restaurantService.dto.*;
import com.example.restaurant.restaurantService.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/restaurant")
public class RestaurantController {

    @Autowired
    RestaurantService restaurantService;

    @PostMapping(path = "/addRestaurant")
    public ResponseEntity<RestaurantResponseDTO> addRestaurant(@RequestBody RestaurantRequestDTO restaurantRequestDTO){
        System.out.println("name : " + restaurantRequestDTO.getRestaurantName());
        RestaurantResponseDTO addedRestaurant = restaurantService.addRestaurant(restaurantRequestDTO);
        System.out.println("name : " + addedRestaurant.getRestaurantName());
        return ResponseEntity.status(HttpStatus.CREATED).body(addedRestaurant);
    }

    @DeleteMapping(path = "/deleteRestaurant/{restaurant_id}")
    public ResponseEntity<Void> removeRestaurant(@PathVariable Long restaurant_id) {
        restaurantService.removeRestaurant(restaurant_id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(path = "/getRestaurants")
    public ResponseEntity<List<RestaurantResponseDTO>> getRestaurantList(
            @RequestParam(value = "address", required = false, defaultValue = "none") String address) {
        List<RestaurantResponseDTO> restaurantList;
        if(!address.equals("none")) {
            restaurantList = restaurantService.getRestaurantList(address);
        }else {
            restaurantList = null;
        }
        return ResponseEntity.ok(restaurantList);
    }




}
