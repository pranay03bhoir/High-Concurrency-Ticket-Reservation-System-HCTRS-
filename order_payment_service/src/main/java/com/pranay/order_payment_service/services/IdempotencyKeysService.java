package com.pranay.order_payment_service.services;

import com.pranay.order_payment_service.DTO.IdempotencyKeyDto;
import com.pranay.order_payment_service.DTO.PagedResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IdempotencyKeysService {

    IdempotencyKeyDto getIdempotencyKey(IdempotencyKeyDto idempotencyKeyDto);

    PagedResponse<IdempotencyKeyDto> getAllIdempotencyKeys(Pageable pageable);
}
