package com.pranay.booking_gateway.dto;

public record ReservationRequest(
        String eventId,
        int quantity
) {
}
