package com.example.cart.cartService.dto.cart;

import com.example.cart.cartService.dto.cartItem.CartItemDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartResponseDTO {

    private Long cartId;

    private Long userId;

    private Double totalAmount;

    private List<CartItemDTO> cartItems;

}
