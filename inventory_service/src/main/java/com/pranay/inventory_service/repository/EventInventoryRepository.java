package com.pranay.inventory_service.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pranay.inventory_service.models.EventInventory;

@Repository
public interface EventInventoryRepository extends JpaRepository<EventInventory, UUID> {

}
