package com.example.restaurant.restaurantService.exception;


public class RestaurantNotFoundException extends RuntimeException{
    public RestaurantNotFoundException(String message) {
        super(message);
    }
}
