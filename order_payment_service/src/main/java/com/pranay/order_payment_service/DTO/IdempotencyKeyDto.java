package com.pranay.order_payment_service.DTO;

import java.util.UUID;

public record IdempotencyKeyDto(
        UUID key,
        Integer responseStatus
) {
}
