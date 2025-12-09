package com.example.paymentService.PaymentService.repository;

import com.example.paymentService.PaymentService.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment,Long> {
    public Optional<Payment> findByOrderId(Long orderId);
}
