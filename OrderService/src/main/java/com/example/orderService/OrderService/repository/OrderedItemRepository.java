package com.example.orderService.OrderService.repository;

import com.example.orderService.OrderService.model.OrderedItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderedItemRepository extends JpaRepository<OrderedItem,Long> {

}
