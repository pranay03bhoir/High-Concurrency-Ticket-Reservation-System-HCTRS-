package com.pranay.order_payment_service.DTO;

import com.pranay.order_payment_service.models.PaymentStatus;

public record PaymentResult(
        PaymentStatus status,
        int statusCode,
        String transactionId,
        String failureReason
) {
    public boolean isSuccessful() {
        return status == PaymentStatus.SUCCESS;
    }

    // Static factory methods for clean instantiation
    public static PaymentResult success(String transactionId) {
        return new PaymentResult(PaymentStatus.SUCCESS, 200, transactionId, null);
    }

    public static PaymentResult declined(int statusCode, String reason) {
        return new PaymentResult(PaymentStatus.DECLINED, statusCode, null, reason);
    }

    public static PaymentResult failure(int statusCode, String reason) {
        return new PaymentResult(PaymentStatus.GATEWAY_ERROR, statusCode, null, reason);
    }
}
