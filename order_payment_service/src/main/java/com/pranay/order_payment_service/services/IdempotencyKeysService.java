package com.pranay.order_payment_service.services;

import com.pranay.order_payment_service.DTO.IdempotencyKeyDto;
import com.pranay.order_payment_service.DTO.PagedResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public interface IdempotencyKeysService {

    Boolean getIdempotencyKey(IdempotencyKeyDto idempotencyKeyDto);

    PagedResponse<IdempotencyKeyDto> getAllIdempotencyKeys(Pageable pageable);

    void recordInFlightKey(UUID key);

    void finalizeKeyStatus(UUID key, int responseStatus);

    Optional<Integer> getCachedStatus(UUID key);
}
