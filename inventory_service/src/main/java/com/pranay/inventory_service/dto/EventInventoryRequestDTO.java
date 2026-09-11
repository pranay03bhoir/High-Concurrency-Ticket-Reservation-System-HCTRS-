package com.pranay.inventory_service.dto;

public record EventInventoryRequestDTO(
        int totalCapacity,

        int totalAvailable,

        int reservedStock,

        int soldStock) {

}
