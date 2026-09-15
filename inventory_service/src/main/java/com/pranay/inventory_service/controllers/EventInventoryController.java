package com.pranay.inventory_service.controllers;

import com.pranay.inventory_service.dto.EventInventoryRequestDTO;
import com.pranay.inventory_service.dto.EventInventoryResponseDTO;
import com.pranay.inventory_service.dto.EventInventoryUpdateDTO;
import com.pranay.inventory_service.dto.PagedResponse;
import com.pranay.inventory_service.services.EventInventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class EventInventoryController {

    private final EventInventoryService eventInventoryService;

    @PostMapping
    public ResponseEntity<EventInventoryResponseDTO> createEventInventory(@RequestBody EventInventoryRequestDTO eventInventoryRequestDTO) {
        return new ResponseEntity<>(eventInventoryService.createEventInventory(eventInventoryRequestDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<PagedResponse<EventInventoryResponseDTO>> getAllEventInventory(
            @PageableDefault(page = 0, size = 10, direction = Sort.Direction.DESC) Pageable pageable
    ) {
        return new ResponseEntity<>(eventInventoryService.getAllEventInventories(pageable), HttpStatus.OK);
    }

    @GetMapping("/inventory/{id}")
    public ResponseEntity<EventInventoryResponseDTO> getEventInventoryById(@PathVariable UUID id) {
        return new ResponseEntity<>(eventInventoryService.getEventInventoryById(id), HttpStatus.OK);
    }

    @PatchMapping("/inventory/{id}")
    public ResponseEntity<EventInventoryResponseDTO> updateEventInventory(@PathVariable UUID id, @RequestBody EventInventoryUpdateDTO eventInventoryUpdateDTO) {
        return new ResponseEntity<>(eventInventoryService.updateEventInventory(id, eventInventoryUpdateDTO), HttpStatus.OK);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<EventInventoryResponseDTO> deleteEventInventory(@PathVariable UUID id) {
        return new ResponseEntity<>(eventInventoryService.deleteEventInventory(id), HttpStatus.OK);
    }
}
