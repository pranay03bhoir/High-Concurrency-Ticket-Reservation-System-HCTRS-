package com.pranay.inventory_service.dto;

public record EventInventoryResponseDTO(
        int totalCapacity,

        int totalAvailable,

        int reservedStock,

        int soldStock) {

}
