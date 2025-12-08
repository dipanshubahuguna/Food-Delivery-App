package com.example.orderService.OrderService.mapper;

import com.example.orderService.OrderService.dto.OrderDTO;
import com.example.orderService.OrderService.dto.OrderedItemDTO;
import com.example.orderService.OrderService.enums.OrderStatus;
import com.example.orderService.OrderService.enums.PaymentStatus;
import com.example.orderService.OrderService.model.Order;
import com.example.orderService.OrderService.model.OrderedItem;
import com.example.orderService.OrderService.util.CartItem;
import jakarta.persistence.Column;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

public class OrderMapper {
    public static OrderDTO toResponseDTO(Order order) {

        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderId(order.getOrderId());
        orderDTO.setUserId(order.getUserId());
        orderDTO.setOrderStatus(order.getOrderStatus());
        orderDTO.setCreatedAt(order.getCreatedAt());
        orderDTO.setPaymentStatus(order.getPaymentStatus());
        orderDTO.setTotalAmount(order.getTotalAmount());

        for(OrderedItem orderedItem : order.getOrderedItems()) {
            OrderedItemDTO orderedItemDTO = new OrderedItemDTO();
            orderedItemDTO.setOrderedItemId(orderedItemDTO.getOrderedItemId());
            orderedItemDTO.setMenuItemId(orderedItem.getMenuItemId());
            orderedItemDTO.setMenuItemName(orderedItem.getMenuItemName());
            orderedItemDTO.setItemRestaurantId(orderedItem.getItemRestaurantId());
            orderedItemDTO.setItemQty(orderedItem.getItemQty());
            orderedItemDTO.setItemPrice(orderedItem.getItemPrice());

            orderDTO.getOrderedItemDTOList().add(orderedItemDTO);
        }

        return orderDTO;

    }
}
