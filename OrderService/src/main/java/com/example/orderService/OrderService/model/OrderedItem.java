package com.example.orderService.OrderService.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "ordered_item")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderedItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ordered_item_id")
    private Long orderedItemId;
    @Column(name = "menu_item_id")
    private Long menuItemId;
    @Column(name = "menu_item_name")
    private String menuItemName;
    @Column(name = "item_restaurant_id")
    private Long itemRestaurantId;
    @Column(name = "item_qty")
    private Double itemQty;
    @Column(name = "item_price")
    private Double itemPrice;

    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "order_id")
    private Order order;
}
