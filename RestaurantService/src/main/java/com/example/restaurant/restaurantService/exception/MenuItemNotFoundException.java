package com.example.restaurant.restaurantService.exception;


public class MenuItemNotFoundException extends RuntimeException{
    public MenuItemNotFoundException(String message) {
        super(message);
    }
}
