package com.example.orderService.OrderService.service;

import com.example.orderService.OrderService.dto.OrderDTO;
import com.example.orderService.OrderService.enums.OrderStatus;
import com.example.orderService.OrderService.enums.PaymentStatus;
import com.example.orderService.OrderService.exception.EmptyCartException;
import com.example.orderService.OrderService.exception.NoOrderFoundException;
import com.example.orderService.OrderService.mapper.OrderMapper;
import com.example.orderService.OrderService.model.Order;
import com.example.orderService.OrderService.model.OrderedItem;
import com.example.orderService.OrderService.repository.OrderRepository;
import com.example.orderService.OrderService.util.Cart;
import com.example.orderService.OrderService.util.DummyCart;
import com.example.orderService.OrderService.util.CartItem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Getter
@Setter
@AllArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    @Transactional
    public OrderDTO createOrder(Long userId) {
/*
    -  fetchCart() -> Cart Microservice - cart details
    -  paymentService() -> Payment Service - proceed to payment and waiting for response
    -  saveOrder() -> save order on payment success/fail
    -  clearCart() -> Cart Microservice - clearing cart
    -  notificationService() -> Notification Service - sending notification once order is placed
*/
        // fetchCart() - Dummy cart details
        Cart cartDetails = DummyCart.createDummyCart();

        if(cartDetails.getCartItems().isEmpty())
            throw new EmptyCartException("Cart is empty cannot place order !");

        PaymentStatus paymentStatus = initiatePayment();

        OrderDTO orderDTO = saveOrder(cartDetails,paymentStatus);

        clearCart();

        // should be async - later
        sendNotification();

        return orderDTO;

    }

    public OrderDTO getOrderById(Long orderId) {
         Order order = orderRepository
                .findById(orderId)
                .orElseThrow(() -> new NoOrderFoundException("Order not found !"));

         return OrderMapper.toResponseDTO(order);
    }

    public List<OrderDTO> getOrdersByUserId(Long userId) {
        List<Order> orderList = orderRepository.findAllByUserId(userId);
        List<OrderDTO> orderDTOList = new ArrayList<>();
        for(Order order : orderList){
            orderDTOList.add(OrderMapper.toResponseDTO(order));
        }

        if(orderDTOList.size() == 0) throw new NoOrderFoundException("No Orders found for this user !");

        return orderDTOList;
    }

    private PaymentStatus initiatePayment() {
        int min = 10, max = 50;
        int randomNum = min + (int)(Math.random() * (max - min) + 1);
        try{
            Thread.sleep(1000);
        }catch(Exception e) {
            e.printStackTrace();
        }
        return randomNum % 2 == 0 ? PaymentStatus.FAIL : PaymentStatus.SUCCESS;
    }

    private OrderDTO saveOrder(Cart cart, PaymentStatus paymentStatus) {
        Order order = new Order();
        order.setUserId(cart.getUserId());
        order.setTotalAmount(cart.getTotalAmount());
        order.setOrderStatus(paymentStatus.equals(
                PaymentStatus.SUCCESS) ? OrderStatus.PLACED : OrderStatus.FAILED);
        order.setPaymentStatus(paymentStatus);
        order.setCreatedAt(LocalDateTime.now());
        order.setOrderedItems(new ArrayList<>());


        for(CartItem cartItem : cart.getCartItems()) {
            OrderedItem orderedItem = new OrderedItem();
            orderedItem.setMenuItemId(cartItem.getMenuItemId());
            orderedItem.setMenuItemName(cartItem.getItemName());
            orderedItem.setItemRestaurantId(cartItem.getRestaurantId());
            orderedItem.setItemQty(cartItem.getItemQuantity());
            orderedItem.setItemPrice(cartItem.getItemPrice());

            order.getOrderedItems().add(orderedItem);
            orderedItem.setOrder(order);
        }

        Order savedOrder = orderRepository.save(order);

        return OrderMapper.toResponseDTO(savedOrder);
    }

    private void clearCart() {
        // call Cart Microservice
    }
    private void sendNotification() {
        // call Notification Microservice
    }

}
