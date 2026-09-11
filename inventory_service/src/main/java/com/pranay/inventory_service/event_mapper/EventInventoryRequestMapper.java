package com.pranay.inventory_service.event_mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.pranay.inventory_service.dto.EventInventoryRequestDTO;
import com.pranay.inventory_service.models.EventInventory;

@Mapper(componentModel = "spring")
public interface EventInventoryRequestMapper {
    @Mapping(source = "totalCapacity", target = "totalCapacity")
    @Mapping(source = "totalAvailable", target = "totalAvailable")
    @Mapping(source = "reservedStock", target = "reservedStock")
    @Mapping(source = "soldStock", target = "soldStock")
    EventInventoryRequestDTO toEventInventoryRequestDto(EventInventory eventInventory);

    @Mapping(target = "eventId", ignore = true)
    @Mapping(target = "version", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(source = "totalCapacity", target = "totalCapacity")
    @Mapping(source = "totalAvailable", target = "totalAvailable")
    @Mapping(source = "reservedStock", target = "reservedStock")
    @Mapping(source = "soldStock", target = "soldStock")
    EventInventory toEventInventoryEntity(EventInventoryRequestDTO eventInventoryRequestDTO);
}
