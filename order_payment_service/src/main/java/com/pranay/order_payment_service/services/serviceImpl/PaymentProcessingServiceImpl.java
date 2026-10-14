package com.pranay.order_payment_service.services.serviceImpl;

import com.pranay.order_payment_service.DTO.PaymentResult;
import com.pranay.order_payment_service.services.PaymentProcessingService;
import com.stripe.Stripe;
import com.stripe.exception.CardException;
import com.stripe.exception.RateLimitException;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.net.RequestOptions;
import com.stripe.param.PaymentIntentCreateParams;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentProcessingServiceImpl implements PaymentProcessingService {

    @Value("${stripe.api-key}")
    private String stripeApiKey;

    @Value("${stripe.connect-timeout-ms:3000}")
    private int connectTimeout;

    @Value("${stripe.read-timeout-ms:5000}")
    private int readTimeout;

    @PostConstruct
    private void init() {
        Stripe.apiKey = this.stripeApiKey;
    }

    @Override
    public PaymentResult processingPayment(String userId, Long amount, String idempotencyKey) throws StripeException {

        RequestOptions options = RequestOptions.builder()
                .setIdempotencyKey(idempotencyKey)
                .setConnectTimeout(connectTimeout)
                .setReadTimeout(readTimeout)
                .build();

        try {
            PaymentIntentCreateParams params =
                    PaymentIntentCreateParams.builder()
                            .setAmount(amount)
                            .setCurrency("inr")
                            .setConfirm(true)
                            .putMetadata("userId", userId)
                            .setAutomaticPaymentMethods(
                                    PaymentIntentCreateParams.AutomaticPaymentMethods.builder()
                                            .setEnabled(true)
                                            .build()
                            ).build();
            PaymentIntent intent = PaymentIntent.create(params, options);

            if ("succeeded".equalsIgnoreCase(intent.getStatus())) {
                return PaymentResult.success(intent.getId());
            } else {
                return PaymentResult.declined(402, "PaymentIntent Status: " + intent.getStatus());
            }
        } catch (CardException e) {
            log.warn("Stripe Card Error: code={}, declinedCode={}", e.getCode(), e.getDeclineCode());
            return PaymentResult.declined(e.getStatusCode(), e.getMessage());
        } catch (RateLimitException e) {
            log.error("Stripe Rate Limit exceeded: {}", e.getMessage());
            return PaymentResult.failure(429, "Too many requests to payment provider");

        } catch (StripeException e) {
            log.error("Stripe API communication failed: {}", e.getMessage());
            return PaymentResult.failure(
                    e.getStatusCode() != null ? e.getStatusCode() : 500,
                    e.getMessage()
            );
        }
    }

}

