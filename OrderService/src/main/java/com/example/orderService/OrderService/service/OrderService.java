package com.example.orderService.OrderService.service;

import com.example.orderService.OrderService.dto.OrderDTO;
import com.example.orderService.OrderService.dto.cart.CartItemDTO;
import com.example.orderService.OrderService.dto.cart.CartResponseDTO;
import com.example.orderService.OrderService.dto.payment.PaymentRequestDTO;
import com.example.orderService.OrderService.dto.payment.PaymentResponseDTO;
import com.example.orderService.OrderService.enums.OrderStatus;
import com.example.orderService.OrderService.enums.PaymentStatus;
import com.example.orderService.OrderService.enums.payment.PaymentMode;
import com.example.orderService.OrderService.exception.EmptyCartException;
import com.example.orderService.OrderService.exception.NoOrderFoundException;
import com.example.orderService.OrderService.external.CartClient;
import com.example.orderService.OrderService.external.PaymentClient;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private final CartClient cartClient;
    private final PaymentClient paymentClient;
    private static final Logger log = LoggerFactory.getLogger(OrderService.class);
    @Transactional
    public OrderDTO createOrder(Long userId,PaymentMode paymentMode) {
        log.debug("Placing Order....");
        log.debug("Getting cart details....");
        CartResponseDTO cartDetails = cartClient.getCartDetails(userId);

        if(cartDetails.getCartItems().isEmpty())
            throw new EmptyCartException("Cart is empty cannot place order !");

        log.debug("Creating order....");
        OrderDTO orderDTO = saveOrder(cartDetails,PaymentStatus.PENDING);

        log.debug("Creating Payment object");
        PaymentRequestDTO paymentRequestDTO = new PaymentRequestDTO(
                userId,
                orderDTO.getOrderId(),
                orderDTO.getTotalAmount(),
                paymentMode);

        log.debug("initiating payment");
        PaymentResponseDTO paymentResponseDTO = paymentClient.initiatePayment(paymentRequestDTO);

        if(paymentResponseDTO.getPaymentStatus().equals(PaymentStatus.SUCCESS)){
            log.debug("Payment success");
            cartClient.deleteCart(userId);
            orderDTO = saveOrder(cartDetails,PaymentStatus.SUCCESS);
        }else{
            log.debug("Payment failed");
            orderDTO = saveOrder(cartDetails,PaymentStatus.FAILED);
        }

        log.debug("sending notification");

        // TODO : Async Communication
        sendNotification();

        return orderDTO;
    }

    public OrderDTO getOrderById(Long orderId) {
        log.debug("Getting order details for order id {}", orderId);
         Order order = orderRepository
                .findById(orderId)
                .orElseThrow(() -> new NoOrderFoundException("Order not found !"));

         return OrderMapper.toResponseDTO(order);
    }

    public List<OrderDTO> getOrdersByUserId(Long userId) {
        log.debug("Getting list of order details for user id {}", userId);
        List<Order> orderList = orderRepository.findAllByUserId(userId);
        List<OrderDTO> orderDTOList = new ArrayList<>();
        for(Order order : orderList){
            orderDTOList.add(OrderMapper.toResponseDTO(order));
        }
        if(orderDTOList.size() == 0) throw new NoOrderFoundException("No Orders found for this user !");
        return orderDTOList;
    }

    private OrderDTO saveOrder(CartResponseDTO cart, PaymentStatus paymentStatus) {
        Order order = new Order();
        order.setUserId(cart.getUserId());
        order.setTotalAmount(cart.getTotalAmount());
        order.setOrderStatus(paymentStatus.equals(
                PaymentStatus.SUCCESS) ? OrderStatus.PLACED : OrderStatus.FAILED);
        order.setPaymentStatus(paymentStatus);
        order.setCreatedAt(LocalDateTime.now());
        order.setOrderedItems(new ArrayList<>());


        for(CartItemDTO cartItem : cart.getCartItems()) {
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

    private void sendNotification() {
        // call Notification Microservice
    }

}
