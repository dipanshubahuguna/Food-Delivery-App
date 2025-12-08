package com.example.cart.cartService.service;

import com.example.cart.cartService.dto.cart.CartResponseDTO;
import com.example.cart.cartService.exception.CartItemNotFoundException;
import com.example.cart.cartService.exception.CartNotFoundException;
import com.example.cart.cartService.mapper.CartMapper;
import com.example.cart.cartService.model.Cart;
import com.example.cart.cartService.model.CartItem;
import com.example.cart.cartService.repository.CartRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class CartService {
    private final CartRepository cartRepository;

    private final CartMapper cartMapper;

    public CartResponseDTO addToCart(Long userId,Long restaurantId, Long menuItemId) {
        Cart savedCart = addItemToCart(userId,restaurantId,menuItemId);
        return cartMapper.toResponseDTO(savedCart);
    }

    public CartResponseDTO removeFromCart(Long userId,Long restaurantId, Long menuItemId) {
        Cart cart = removeItemFromCart(userId,restaurantId,menuItemId);
        return cartMapper.toResponseDTO(cart);
    }

    public CartResponseDTO decrementFromCart(Long userId,Long restaurantId,Long menuItemId) {
        Cart cart = decrementItemFromCart(userId,restaurantId,menuItemId);
        return cartMapper.toResponseDTO(cart);
    }

    @Transactional
    public void clearCart(Long userId) {
        int rowsAffected = cartRepository.deleteByUserId(userId);
        if(rowsAffected == 0) throw new CartNotFoundException("Cart is not present for the user !");
    }

    @Transactional
    public Cart addItemToCart(Long userId,Long restaurantId, Long menuItemId) {
        /*
            For Now assuming that Restaurant Microservice gives correct MenuItem and Restaurant details.
        */
        Double ItemPrice = 20d;
        Long ItemQuantity = 1l;
        String ItemName = "Idli";

        // If creating cart for first time
        Cart cart = getCartDetails(userId);

        //  Check If Item already exists
        CartItem existingCartItem = getCartItemDetails(cart,restaurantId,menuItemId);
        if(existingCartItem == null) {
            CartItem cartItem = CartItem.builder()
                    .menuItemId(menuItemId)
                    .itemName(ItemName)
                    .itemPrice(ItemPrice)
                    .itemQuantity(ItemQuantity)
                    .restaurantId(restaurantId)
                    .build();
            cartItem.setCart(cart);
            cart.getCartItem().add(cartItem);
        }else {
            existingCartItem.setItemQuantity(existingCartItem.getItemQuantity()+ItemQuantity);
        }
        Double updatedAmount = cart.getTotalAmount() + ItemPrice*ItemQuantity;
        cart.setTotalAmount(updatedAmount);

        return cartRepository.save(cart);
    }

    @Transactional
    public Cart removeItemFromCart(Long userId,Long restaurantId, Long menuItemId) {
        Cart cart = getExistingCartDetails(userId);
        CartItem itemToRemove = getItemDetails(cart,restaurantId,menuItemId);

        Double removedItemPrice = itemToRemove.getItemQuantity() * itemToRemove.getItemPrice();
        cart.setTotalAmount(cart.getTotalAmount() - removedItemPrice);
        cart.getCartItem().remove(itemToRemove);

        return cartRepository.save(cart);
    }

    @Transactional
    public Cart decrementItemFromCart(Long userId, Long restaurantId, Long menuItemId) {
        Cart cart = getExistingCartDetails(userId);
        CartItem itemToDecrement = getItemDetails(cart,restaurantId,menuItemId);

        Double removedItemPrice = itemToDecrement.getItemPrice();
        if(itemToDecrement.getItemQuantity() > 1){
            itemToDecrement.setItemQuantity(itemToDecrement.getItemQuantity() - 1);
        }else {
            cart.getCartItem().remove(itemToDecrement);
        }
        cart.setTotalAmount(cart.getTotalAmount() - removedItemPrice);
        return cartRepository.save(cart);
    }

    public CartResponseDTO getCart(Long userId) {
        Cart cart = getExistingCartDetails(userId);
        return cartMapper.toResponseDTO(cart);
    }

    public Cart getExistingCartDetails(Long userId) {
        return cartRepository
                .findByUserId(userId)
                .orElseThrow(() -> new CartNotFoundException("Cart is not present for the user"));
    }

    private Cart createCart(Long userId) {
        return new Cart(userId);
    }

    private Cart getCartDetails(Long userId) {
        return cartRepository.findByUserId(userId).orElseGet(() -> createCart(userId));
    }

    private CartItem getCartItemDetails(Cart cart, Long restaurantId, Long menuItemId) {
        return cart.getCartItem()
                .stream()
                .filter(cartItem ->
                        cartItem.getRestaurantId().equals(restaurantId)
                                && cartItem.getMenuItemId().equals(menuItemId))
                .findFirst()
                .orElse(null);
    }

    private CartItem getItemDetails(Cart cart, Long restaurantId, Long menuItemId) {
        return cart.getCartItem()
                .stream()
                .filter(cartItem ->
                        cartItem.getRestaurantId().equals(restaurantId)
                                && cartItem.getMenuItemId().equals(menuItemId))
                .findFirst()
                .orElseThrow(() -> new CartItemNotFoundException("Menu Item not found in the Cart !"));
    }
}