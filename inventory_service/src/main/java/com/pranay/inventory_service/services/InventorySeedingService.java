package com.pranay.inventory_service.services;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface InventorySeedingService {

    void seedingInventoryToRedis(UUID eventId);

}
