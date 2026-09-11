package com.pranay.inventory_service.services.service_impl;

import com.pranay.inventory_service.dto.EventInventoryRequestDTO;
import com.pranay.inventory_service.dto.EventInventoryResponseDTO;
import com.pranay.inventory_service.dto.PagedResponse;
import com.pranay.inventory_service.event_mapper.EventInventoryRequestMapper;
import com.pranay.inventory_service.event_mapper.EventInventoryResponseMapper;
import com.pranay.inventory_service.models.EventInventory;
import com.pranay.inventory_service.repository.EventInventoryRepository;
import com.pranay.inventory_service.services.EventInventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EventInventoryServiceImpl implements EventInventoryService {

    private final EventInventoryRepository eventInventoryRepository;
//    private final EventInventoryRequestDTO eventInventoryRequestDTO;
//    private final EventInventoryResponseDTO eventInventoryResponseDTO;
    private final EventInventoryRequestMapper eventInventoryRequestMapper;
    private final EventInventoryResponseMapper eventInventoryResponseMapper;

    @Override
    public EventInventoryResponseDTO createEventInventory(EventInventoryRequestDTO eventInventoryRequestDTO) {
        EventInventory createdInventory = eventInventoryRequestMapper.toEventInventoryEntity(eventInventoryRequestDTO);
        EventInventory savedInventory = eventInventoryRepository.save(createdInventory);
        return eventInventoryResponseMapper.toEventInventoryResponseDto(savedInventory);
    }

    @Override
    public PagedResponse<EventInventoryResponseDTO> getAllEventInventories(Pageable pageable) {
        Page<EventInventory> inventories = eventInventoryRepository.findAll(pageable);
        if (inventories.isEmpty()) {
            throw new RuntimeException("No inventories found!");
        }
        return eventInventoryResponseMapper.toPagedResponse(inventories);
    }
}
