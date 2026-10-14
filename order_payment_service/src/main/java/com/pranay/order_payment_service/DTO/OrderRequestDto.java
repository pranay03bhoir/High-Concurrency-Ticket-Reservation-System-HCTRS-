package com.pranay.order_payment_service.DTO;

import com.pranay.order_payment_service.models.Status;

import java.util.UUID;

public record OrderRequestDto(
        String reservationId,
        UUID userId,
        UUID eventId,
        String amount,
        Status status
) {
}
