package com.pranay.inventory_service.event_mapper;

import com.pranay.inventory_service.dto.PagedResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.pranay.inventory_service.dto.EventInventoryResponseDTO;
import com.pranay.inventory_service.models.EventInventory;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EventInventoryResponseMapper {
    @Mapping(source = "totalCapacity", target = "totalCapacity")
    @Mapping(source = "totalAvailable", target = "totalAvailable")
    @Mapping(source = "reservedStock", target = "reservedStock")
    @Mapping(source = "soldStock", target = "soldStock")
    EventInventoryResponseDTO toEventInventoryResponseDto(EventInventory eventInventory);

    @Mapping(source = "totalCapacity", target = "totalCapacity")
    @Mapping(source = "totalAvailable", target = "totalAvailable")
    @Mapping(source = "reservedStock", target = "reservedStock")
    @Mapping(source = "soldStock", target = "soldStock")
    List<EventInventoryResponseDTO> toEventInventoryResponseDtoList(List<EventInventory> eventInventory);

    @Mapping(target = "eventId", ignore = true)
    @Mapping(target = "version", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(source = "totalCapacity", target = "totalCapacity")
    @Mapping(source = "totalAvailable", target = "totalAvailable")
    @Mapping(source = "reservedStock", target = "reservedStock")
    @Mapping(source = "soldStock", target = "soldStock")
    EventInventory toEventInventoryEntity(EventInventoryResponseDTO eventInventoryResponseDTO);

    default PagedResponse<EventInventoryResponseDTO> toPagedResponse(Page<EventInventory> eventInventories) {
        List<EventInventoryResponseDTO> content = toEventInventoryResponseDtoList(eventInventories.getContent());

        return new PagedResponse<>(
                content,
                eventInventories.getNumber(),
                eventInventories.getSize(),
                eventInventories.getTotalElements(),
                eventInventories.getTotalPages(),
                eventInventories.isLast()
        );
    }
}
