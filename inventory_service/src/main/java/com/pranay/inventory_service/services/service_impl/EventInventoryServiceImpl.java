package com.pranay.inventory_service.services.service_impl;

import com.pranay.inventory_service.dto.EventInventoryRequestDTO;
import com.pranay.inventory_service.dto.EventInventoryResponseDTO;
import com.pranay.inventory_service.dto.EventInventoryUpdateDTO;
import com.pranay.inventory_service.dto.PagedResponse;
import com.pranay.inventory_service.event_mapper.EventInventoryRequestMapper;
import com.pranay.inventory_service.event_mapper.EventInventoryResponseMapper;
import com.pranay.inventory_service.event_mapper.EventInventoryUpdateMapper;
import com.pranay.inventory_service.exceptions.ResourceNotFoundException;
import com.pranay.inventory_service.models.EventInventory;
import com.pranay.inventory_service.repository.EventInventoryRepository;
import com.pranay.inventory_service.services.EventInventoryService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EventInventoryServiceImpl implements EventInventoryService {

    private final EventInventoryRepository eventInventoryRepository;
    //    private final EventInventoryRequestDTO eventInventoryRequestDTO;
//    private final EventInventoryResponseDTO eventInventoryResponseDTO;
    private final EventInventoryRequestMapper eventInventoryRequestMapper;
    private final EventInventoryResponseMapper eventInventoryResponseMapper;
    private final EventInventoryUpdateMapper eventInventoryUpdateMapper;

    @Override
    @Transactional
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

    @Override
    public EventInventoryResponseDTO getEventInventoryById(UUID id) {
        EventInventory eventInventory = eventInventoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Event Inventory Item not found!!!"));
        return eventInventoryResponseMapper.toEventInventoryResponseDto(eventInventory);
    }

    @Override
    @Transactional
    public EventInventoryResponseDTO updateEventInventory(UUID id, EventInventoryUpdateDTO eventInventoryUpdateDTO) {

        EventInventory eventInventory = eventInventoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No Event inventory found, Please check the ID again"));

        EventInventory eventInventoryEntity = eventInventoryUpdateMapper.updateInventoryFromDto(eventInventoryUpdateDTO, eventInventory);
        EventInventory updatedEventInventory = eventInventoryRepository.save(eventInventoryEntity);
        return eventInventoryResponseMapper.toEventInventoryResponseDto(updatedEventInventory);

    }

    @Override
    @Transactional
    public EventInventoryResponseDTO deleteEventInventory(UUID id) {
        EventInventory eventInventory = eventInventoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No Event inventory found, Please check the ID again"));
        eventInventoryRepository.deleteById(id);
        return eventInventoryResponseMapper.toEventInventoryResponseDto(eventInventory);
    }
}
