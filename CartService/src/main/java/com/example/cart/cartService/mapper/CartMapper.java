package com.example.cart.cartService.mapper;

import com.example.cart.cartService.dto.cart.CartResponseDTO;
import com.example.cart.cartService.dto.cartItem.CartItemDTO;
import com.example.cart.cartService.model.Cart;
import com.example.cart.cartService.model.CartItem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CartMapper {

    public CartResponseDTO toResponseDTO(Cart cart) {
        CartResponseDTO cartResponseDTO = new CartResponseDTO();
        cartResponseDTO.setCartId(cart.getCartId());
        cartResponseDTO.setUserId(cart.getUserId());
        cartResponseDTO.setTotalAmount(cart.getTotalAmount());
        List<CartItemDTO> cartItemsList = new ArrayList<>();

        for(CartItem cartItem : cart.getCartItem()) {
            cartItemsList.add(new CartItemDTO(
                    cartItem.getCartItemId(),
                    cartItem.getMenuItemId(),
                    cartItem.getRestaurantId(),
                    cartItem.getItemName(),
                    cartItem.getItemQuantity(),
                    cartItem.getItemPrice()));
        }

        cartResponseDTO.setCartItems(cartItemsList);

        return cartResponseDTO;
    }

}
