package com.pranay.inventory_service.event_mapper;

import org.mapstruct.*;

import com.pranay.inventory_service.dto.EventInventoryUpdateDTO;
import com.pranay.inventory_service.models.EventInventory;

@Mapper(componentModel = "spring")
public interface EventInventoryUpdateMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "totalCapacity", target = "totalCapacity")
    @Mapping(source = "totalAvailable", target = "totalAvailable")
    @Mapping(source = "reservedStock", target = "reservedStock")
    @Mapping(source = "soldStock", target = "soldStock")
    EventInventory updateInventoryFromDto(EventInventoryUpdateDTO eventInventoryUpdateDTO, @MappingTarget EventInventory eventInventory);

//    @Mapping(target = "eventId", ignore = true)
//    @Mapping(target = "version", ignore = true)
//    @Mapping(target = "updatedAt", ignore = true)
//    @Mapping(source = "totalCapacity", target = "totalCapacity")
//    @Mapping(source = "totalAvailable", target = "totalAvailable")
//    @Mapping(source = "reservedStock", target = "reservedStock")
//    @Mapping(source = "soldStock", target = "soldStock")
//    EventInventory toEventInventoryEntity(EventInventoryUpdateDTO eventInventoryUpdateDTO);
}
