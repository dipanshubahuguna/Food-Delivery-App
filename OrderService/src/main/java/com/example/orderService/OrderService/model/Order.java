package com.example.orderService.OrderService.model;

import com.example.orderService.OrderService.enums.OrderStatus;
import com.example.orderService.OrderService.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long orderId;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "total_amt")
    private Double totalAmount;
    @Column(name = "order_sts")
    private OrderStatus orderStatus;
    @Column(name = "payment_sts")
    private PaymentStatus paymentStatus;
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderedItem> orderedItems;
}
