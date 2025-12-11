package com.example.orderService.OrderService.external;


import com.example.orderService.OrderService.dto.cart.CartResponseDTO;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "Cart-Service",
            url = "http://localhost:8082",
            path = "/cart")
public interface CartClient {

    @GetMapping(path = "/get-cart/{user_id}")
    public CartResponseDTO getCartDetails(@PathVariable("user_id") Long userId);

    @DeleteMapping(path = "/delete-cart/{user_id}")
    public Void deleteCart(
            @PathVariable("user_id") Long userId);
}
