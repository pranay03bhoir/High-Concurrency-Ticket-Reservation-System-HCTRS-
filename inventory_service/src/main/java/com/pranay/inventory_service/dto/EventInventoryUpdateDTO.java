package com.pranay.inventory_service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EventInventoryUpdateDTO {
    private Integer totalCapacity;

    private Integer totalAvailable;

    private Integer reservedStock;

    private Integer soldStock;
}
