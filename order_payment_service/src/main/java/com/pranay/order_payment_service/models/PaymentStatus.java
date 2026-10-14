package com.pranay.order_payment_service.models;

public enum PaymentStatus {
    PENDING,
    SUCCESS,
    DECLINED,
    GATEWAY_ERROR,
    TIMEOUT
}
