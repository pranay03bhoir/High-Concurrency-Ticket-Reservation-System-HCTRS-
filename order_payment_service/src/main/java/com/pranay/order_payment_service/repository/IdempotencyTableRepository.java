package com.pranay.order_payment_service.repository;

import com.pranay.order_payment_service.models.IdempotencyKeyTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IdempotencyTableRepository extends JpaRepository<IdempotencyKeyTable, UUID> {
}
