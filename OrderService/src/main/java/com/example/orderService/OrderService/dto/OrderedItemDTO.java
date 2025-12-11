package com.example.orderService.OrderService.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Component
public class OrderedItemDTO {
    private Long orderedItemId;
    private Long menuItemId;
    private String menuItemName;
    private Long itemRestaurantId;
    private Double itemQty;
    private Double itemPrice;
}
