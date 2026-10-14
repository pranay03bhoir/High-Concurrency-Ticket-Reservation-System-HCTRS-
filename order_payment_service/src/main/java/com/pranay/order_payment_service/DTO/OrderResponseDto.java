package com.pranay.order_payment_service.DTO;

import com.pranay.order_payment_service.models.Status;

import java.util.UUID;

public record OrderResponseDto(
        UUID userId,
        UUID eventId,
        String reservationId,
        String amount,
        Status status

) {
}
