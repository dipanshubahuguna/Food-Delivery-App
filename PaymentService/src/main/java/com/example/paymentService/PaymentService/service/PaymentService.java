package com.example.paymentService.PaymentService.service;

import com.example.paymentService.PaymentService.dto.PaymentRequestDTO;
import com.example.paymentService.PaymentService.dto.PaymentResponseDTO;
import com.example.paymentService.PaymentService.enums.PaymentStatus;
import com.example.paymentService.PaymentService.exception.PaymentNotFoundException;
import com.example.paymentService.PaymentService.exception.PaymentStrategyNotFoundException;
import com.example.paymentService.PaymentService.mapper.PaymentMapper;
import com.example.paymentService.PaymentService.model.Payment;
import com.example.paymentService.PaymentService.repository.PaymentRepository;

import org.springframework.stereotype.Service;

import java.util.Map;
@Service
public class PaymentService {

    private final PaymentMapper paymentMapper;
    private final PaymentRepository paymentRepository;
    private final Map<String, PaymentStrategy> paymentStrategyMap;

    public PaymentService(
            PaymentMapper paymentMapper,
            PaymentRepository paymentRepository,
            Map<String, PaymentStrategy> paymentStrategyMap) {
        this.paymentMapper = paymentMapper;
        this.paymentStrategyMap = paymentStrategyMap;
        this.paymentRepository = paymentRepository;
    }

    public PaymentResponseDTO processPayment(PaymentRequestDTO paymentRequestDTO) {
        PaymentStrategy paymentStrategy = paymentStrategyMap.get(paymentRequestDTO.getPaymentMode().name());

        if(paymentStrategy == null) throw new PaymentStrategyNotFoundException("Payment strategy not found !");

        PaymentStatus paymentStatus = paymentStrategy.pay(paymentRequestDTO);

        Payment savedPayment = paymentRepository.save(
                paymentMapper.toEntity(paymentRequestDTO,paymentStatus));

        return paymentMapper.toResponseDTO(savedPayment);

    }

    public PaymentResponseDTO getPaymentDetails(Long orderId) {
        Payment payment = paymentRepository
                .findByOrderId(orderId)
                .orElseThrow(() ->
                        new PaymentNotFoundException("Payment not found for this order !"));

        return paymentMapper.toResponseDTO(payment);
    }

}