package com.example.cart.cartService.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "cart_item")
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_item_id")
    private Long cartItemId;
    @Column(name = "menu_item_id")
    private Long menuItemId;
    @Column(name = "item_name")
    private String itemName;
    @Column(name = "item_qty")
    private Long itemQuantity;
    @Column(name = "item_price")
    private Double itemPrice;
    @Column(name = "restaurant_id")
    private Long restaurantId;
    @ManyToOne
    @JoinColumn(name = "cart_id", referencedColumnName = "cart_id")
    private Cart cart;

}
