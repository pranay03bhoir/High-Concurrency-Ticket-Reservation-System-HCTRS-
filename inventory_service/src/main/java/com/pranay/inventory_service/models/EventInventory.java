package com.pranay.inventory_service.models;

import java.time.Instant;
import java.util.UUID;

import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "event_inventory")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EventInventory {

    @Id
    @GeneratedValue
    @UuidGenerator
    @Column(name = "event_id", nullable = false)
    private UUID eventId;

    @Column(name = "total_capacity", nullable = false)
    private int totalCapacity;

    @Column(name = "total_available")
    private int totalAvailable;

    @Column(name = "reserved_stock", nullable = false)
    private int reservedStock;

    @Column(name = "sold_stock", nullable = false)
    private int soldStock;

    @Version
    @Column(name = "version")
    private long version;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private Instant updatedAt;

    @PrePersist
    @PreUpdate
    private void totalAvailableSeeder() {
        this.totalAvailable = this.totalCapacity - this.soldStock;
    }
}
