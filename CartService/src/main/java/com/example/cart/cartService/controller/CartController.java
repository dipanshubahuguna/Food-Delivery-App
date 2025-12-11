package com.example.cart.cartService.controller;

import com.example.cart.cartService.dto.cart.CartResponseDTO;
import com.example.cart.cartService.service.CartService;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/cart")
@AllArgsConstructor
public class CartController {
    private final CartService cartService;
    private static final Logger log = LoggerFactory.getLogger(CartController.class);
    @GetMapping(path = "/get-cart/{user_id}")
    public ResponseEntity<CartResponseDTO> getCartDetails(
            @PathVariable("user_id") @NotNull @Positive Long userId) {
        log.info("Received request to GET cart details for user id {}",userId);
        return ResponseEntity.ok(cartService.getCart(userId));
    }

    @PostMapping(path = "/add-to-cart/{user_id}/restaurant/{restaurant_id}/menu-item/{menu_item_id}")
    public ResponseEntity<CartResponseDTO> addToCart(
            @PathVariable("user_id") @NotNull @Positive Long userId,
            @PathVariable("restaurant_id") @NotNull @Positive Long restaurantId,
            @PathVariable("menu_item_id") @NotNull @Positive Long menuItemId ){
        log.info("Received request for add to cart for user id {} restaurant id {} and menu item id {}"
                ,userId,restaurantId,menuItemId);
        return ResponseEntity.status(HttpStatus.CREATED).body(cartService.addToCart(userId,restaurantId,menuItemId));
    }

    @PutMapping(path = "/remove-from-cart/{user_id}/restaurant/{restaurant_id}/menu-item/{menu_item_id}")
    public ResponseEntity<CartResponseDTO> removeFromCart(
            @PathVariable("user_id") @NotNull @Positive Long userId,
            @PathVariable("restaurant_id") @NotNull @Positive Long restaurantId,
            @PathVariable("menu_item_id") @NotNull @Positive Long menuItemId) {
        log.info("Received request for remove form cart for user id {} restaurant id {} and menu item id {}"
                ,userId,restaurantId,menuItemId);
        return ResponseEntity
                .ok(cartService.removeFromCart(userId,restaurantId,menuItemId));
    }

    @PutMapping(path = "/decrement-from-cart/{user_id}/restaurant/{restaurant_id}/menu-item/{menu_item_id}")
    public ResponseEntity<CartResponseDTO> decrementFromCart(
            @PathVariable("user_id") @NotNull @Positive Long userId,
            @PathVariable("restaurant_id") @NotNull @Positive Long restaurantId,
            @PathVariable("menu_item_id") @NotNull @Positive Long menuItemId) {
        log.info("Received request for decrement from cart for user id {} restaurant id {} and menu item id {}"
                ,userId,restaurantId,menuItemId);
        return ResponseEntity
                .ok(cartService.decrementFromCart(userId,restaurantId,menuItemId));
    }

    @DeleteMapping(path = "/delete-cart/{user_id}")
    public ResponseEntity<Void> deleteCart(
            @PathVariable("user_id") Long userId) {
        log.info("Received request to delete cart for user id{}",userId);
        cartService.clearCart(userId);
        return ResponseEntity.noContent().build();
    }

}
