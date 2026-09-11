package com.pranay.inventory_service.event_mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.pranay.inventory_service.dto.EventInventoryUpdateDTO;
import com.pranay.inventory_service.models.EventInventory;

@Mapper(componentModel = "spring")
public interface EventInventoryUpdateMapper {

    @Mapping(source = "totalCapacity", target = "totalCapacity")
    EventInventoryUpdateDTO toEventInventoryUpdateDto(EventInventory eventInventory);

    @Mapping(target = "eventId", ignore = true)
    @Mapping(target = "version", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "totalAvailable", ignore = true)
    @Mapping(target = "reservedStock", ignore = true)
    @Mapping(target = "soldStock", ignore = true)
    @Mapping(source = "totalCapacity", target = "totalCapacity")
    EventInventory toEventInventoryEntity(EventInventoryUpdateDTO eventInventoryUpdateDTO);
}
