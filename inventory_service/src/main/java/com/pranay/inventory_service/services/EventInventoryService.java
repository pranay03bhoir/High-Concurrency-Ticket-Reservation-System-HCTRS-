package com.pranay.inventory_service.services;

import com.pranay.inventory_service.dto.EventInventoryRequestDTO;
import com.pranay.inventory_service.dto.EventInventoryResponseDTO;
import com.pranay.inventory_service.dto.PagedResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface EventInventoryService {

    EventInventoryResponseDTO createEventInventory(EventInventoryRequestDTO eventInventoryRequestDTO);

    PagedResponse<EventInventoryResponseDTO> getAllEventInventories(Pageable pageable);
}
