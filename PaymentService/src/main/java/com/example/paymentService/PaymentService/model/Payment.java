package com.example.paymentService.PaymentService.model;


import com.example.paymentService.PaymentService.enums.PaymentMode;
import com.example.paymentService.PaymentService.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "payment")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private Long paymentId;
    @Column(name = "order_id")
    private Long orderId;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "amount")
    private Double amount;
    @Column(name = "payment_mode")
    private PaymentMode paymentMode;
    @Column(name = "payment_sts")
    private PaymentStatus paymentStatus;
    @Column(name = "payment_ts")
    private LocalDateTime paymentTime;

}
