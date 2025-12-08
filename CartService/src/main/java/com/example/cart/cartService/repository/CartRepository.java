package com.example.cart.cartService.repository;

import com.example.cart.cartService.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart,Long> {
    public Optional<Cart> findByUserId(Long user_id);
    public int deleteByUserId(Long user_id);
}
