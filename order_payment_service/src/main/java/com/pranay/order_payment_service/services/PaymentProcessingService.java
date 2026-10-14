package com.pranay.order_payment_service.services;

import com.pranay.order_payment_service.DTO.PaymentResult;
import com.stripe.exception.StripeException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public interface PaymentProcessingService {
    PaymentResult processingPayment(String userId, Long amount, String idempotencyKey) throws StripeException;
}
