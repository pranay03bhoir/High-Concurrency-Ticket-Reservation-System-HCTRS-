package com.pranay.order_payment_service.DTO;

import java.util.List;

public record PagedResponse<E>(
        List<IdempotencyKeyDto> content,
        int pageNumber,
        int pageSize,
        long totalElements,
        int totalPages,
        boolean isLast
) {
}
