package com.example.cart.cartService.service;

import com.example.cart.cartService.dto.cart.CartResponseDTO;
import com.example.cart.cartService.dto.menuItem.MenuItemDTO;
import com.example.cart.cartService.exception.CartItemNotFoundException;
import com.example.cart.cartService.exception.CartNotFoundException;
import com.example.cart.cartService.external.MenuItemClient;
import com.example.cart.cartService.mapper.CartMapper;
import com.example.cart.cartService.model.Cart;
import com.example.cart.cartService.model.CartItem;
import com.example.cart.cartService.repository.CartRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class CartService {
    private final CartRepository cartRepository;

    private final CartMapper cartMapper;

    private final MenuItemClient menuItemClient;

    private static final Logger log = LoggerFactory.getLogger(CartService.class);

    public CartResponseDTO addToCart(Long userId,Long restaurantId, Long menuItemId) {
        log.debug("Adding menu item with id {} of restaurant with id {} for user with id {}"
                ,menuItemId,restaurantId,userId);
        Cart savedCart = addItemToCart(userId,restaurantId,menuItemId);
        return cartMapper.toResponseDTO(savedCart);
    }

    public CartResponseDTO removeFromCart(Long userId,Long restaurantId, Long menuItemId) {
        log.debug("Removing menu item with id {} of restaurant with id {} for user with id {}"
                ,menuItemId,restaurantId,userId);
        Cart cart = removeItemFromCart(userId,restaurantId,menuItemId);
        return cartMapper.toResponseDTO(cart);
    }

    public CartResponseDTO decrementFromCart(Long userId,Long restaurantId,Long menuItemId) {
        log.debug("Decrementing menu item with id {} of restaurant with id {} for user with id {}"
                ,menuItemId,restaurantId,userId);
        Cart cart = decrementItemFromCart(userId,restaurantId,menuItemId);
        return cartMapper.toResponseDTO(cart);
    }

    @Transactional
    public void clearCart(Long userId) {
        log.debug("clearing cart for user with id {}",userId);
        int rowsAffected = cartRepository.deleteByUserId(userId);
        if(rowsAffected == 0) throw new CartNotFoundException("Cart is not present for the user !");
        log.debug("cart deleted");
    }

    @Transactional
    public Cart addItemToCart(Long userId,Long restaurantId, Long menuItemId) {

        // Restaurant Microservice call - Fetching Menu Item details.
        log.debug("Calling restaurant microservice to get menu item details for menu item id " +
                "{} and restaurant id {}",menuItemId, restaurantId);
        MenuItemDTO menuItem= menuItemClient.getMenuItem(restaurantId, menuItemId);

        // Getting cart for the user either existing or new if not present.
        log.debug("Getting cart for the user with user id {}",userId);
        Cart cart = getCartDetails(userId);

        //  Check If Item already exists
        CartItem existingCartItem = getCartItemDetails(cart,restaurantId,menuItemId);
        if(existingCartItem == null) {
            log.debug("Cart doesn't exists, creating new cart..");
            CartItem cartItem = CartItem.builder()
                    .menuItemId(menuItemId)
                    .itemName(menuItem.getItemName())
                    .itemPrice(menuItem.getItemPrice())
                    .itemQuantity(1d)
                    .restaurantId(restaurantId)
                    .build();
            cartItem.setCart(cart);
            cart.getCartItem().add(cartItem);
        }else {
            log.debug("Cart exists, updating the item quantity.");
            existingCartItem.setItemQuantity(existingCartItem.getItemQuantity() + 1);
        }
        Double updatedAmount = cart.getTotalAmount() + menuItem.getItemPrice();
        log.debug("Updating total amount from {} to {}",cart.getTotalAmount() ,updatedAmount);
        cart.setTotalAmount(updatedAmount);
        log.debug("saving data to DB");
        return cartRepository.save(cart);
    }

    @Transactional
    public Cart removeItemFromCart(Long userId,Long restaurantId, Long menuItemId) {
        log.debug("Getting existing cart for the user with user id {}",userId);
        Cart cart = getExistingCartDetails(userId);
        log.debug("Getting details of item to remove with menu id {} and restaurant id {}",menuItemId,restaurantId);
        CartItem itemToRemove = getItemDetails(cart,restaurantId,menuItemId);
        Double removedItemPrice = itemToRemove.getItemQuantity() * itemToRemove.getItemPrice();
        log.debug("Price of Item to remove {}",removedItemPrice);
        log.debug("Current cart total amount {}",cart.getTotalAmount());
        cart.setTotalAmount(cart.getTotalAmount() - removedItemPrice);
        log.debug("Updated cart total amount",cart.getTotalAmount());
        cart.getCartItem().remove(itemToRemove);

        return cartRepository.save(cart);
    }

    @Transactional
    public Cart decrementItemFromCart(Long userId, Long restaurantId, Long menuItemId) {
        log.debug("Getting existing cart for the user with user id {}",userId);
        Cart cart = getExistingCartDetails(userId);
        CartItem itemToDecrement = getItemDetails(cart,restaurantId,menuItemId);

        Double removedItemPrice = itemToDecrement.getItemPrice();
        log.debug("Price of Item to remove {}",removedItemPrice);
        if(itemToDecrement.getItemQuantity() > 1){
            log.debug("Reducing item's qty");
            itemToDecrement.setItemQuantity(itemToDecrement.getItemQuantity() - 1);
        }else {
            log.debug("One quantity of item present, removing item");
            cart.getCartItem().remove(itemToDecrement);
        }
        cart.setTotalAmount(cart.getTotalAmount() - removedItemPrice);
        log.debug("Updated cart total amount",cart.getTotalAmount());
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