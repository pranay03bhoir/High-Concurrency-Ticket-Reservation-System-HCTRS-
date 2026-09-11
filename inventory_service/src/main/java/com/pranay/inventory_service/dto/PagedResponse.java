package com.pranay.inventory_service.dto;

import com.pranay.inventory_service.models.EventInventory;

import java.util.List;

public record PagedResponse<E>(
        List<EventInventoryResponseDTO> content,
        int pageNumber,
        int pageSize,
        long totalElements,
        int totalPages,
        boolean isLast
) {
}
