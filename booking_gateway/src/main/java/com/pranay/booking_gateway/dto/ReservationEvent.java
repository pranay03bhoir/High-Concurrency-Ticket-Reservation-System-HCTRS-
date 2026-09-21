package com.pranay.booking_gateway.dto;

public record ReservationEvent(
        String reservationId,
        String eventId,
        String userId,
        long timeStamp
) {
}
