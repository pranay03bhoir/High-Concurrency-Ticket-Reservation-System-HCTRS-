package com.pranay.inventory_service.services.service_impl;

import com.pranay.inventory_service.exceptions.ResourceNotFoundException;
import com.pranay.inventory_service.models.EventInventory;
import com.pranay.inventory_service.repository.EventInventoryRepository;
import com.pranay.inventory_service.services.InventorySeedingService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InventorySeedingServiceImpl implements InventorySeedingService {

    private final EventInventoryRepository eventInventoryRepository;
    private final StringRedisTemplate stringRedisTemplate;

    @Override
    public void seedingInventoryToRedis(UUID eventId) {
        EventInventory eventInventory = eventInventoryRepository.findById(eventId)
                .orElseThrow(() -> new ResourceNotFoundException("Inventory event not found!!!"));
        int availableTickets = eventInventory.getTotalCapacity() - eventInventory.getSoldStock();
        String stockKey = "event:" + eventId + ":tickets_available";
        stringRedisTemplate.opsForValue().set(stockKey, String.valueOf(availableTickets));

    }
}
