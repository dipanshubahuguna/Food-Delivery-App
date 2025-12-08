package com.example.cart.cartService.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id")
    private Long cartId;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "total_amount")
    private Double totalAmount;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> cartItem = new ArrayList<>();

    public Cart(Long userId) {
        this.userId = userId;
        this.totalAmount = 0d;
    }
}
