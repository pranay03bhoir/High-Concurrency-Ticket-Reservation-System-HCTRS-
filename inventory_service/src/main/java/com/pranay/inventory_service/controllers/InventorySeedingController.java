package com.pranay.inventory_service.controllers;

import com.pranay.inventory_service.services.InventorySeedingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/inventory")
public class InventorySeedingController {

    private final InventorySeedingService inventorySeedingService;

    @PostMapping("/events/{eventId}/publish")
    public ResponseEntity<String> seedingRedisWithInventory(@PathVariable UUID eventId) {
        inventorySeedingService.seedingInventoryToRedis(eventId);
        return ResponseEntity.ok("Event inventory successfully pushed to Redis.");
    }

}
