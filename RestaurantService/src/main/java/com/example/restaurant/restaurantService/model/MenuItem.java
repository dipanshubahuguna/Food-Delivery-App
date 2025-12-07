package com.example.restaurant.restaurantService.model;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "menu_item")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "menuItemId"
)
public class MenuItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long menuItemId;
    @Column(name = "item_name")
    private String itemName;
    @Column(name = "item_quantity")
    private Double itemQuantity;
    @Column(name = "item_price")
    private Double itemPrice;

    @ManyToOne
    @JoinColumn(name = "restaurant_id", referencedColumnName = "restaurant_id")
    private Restaurant restaurant;
}
