package com.pranay.order_payment_service.services.serviceImpl;

import com.pranay.order_payment_service.DTO.IdempotencyKeyDto;
import com.pranay.order_payment_service.DTO.PagedResponse;
import com.pranay.order_payment_service.config.IdempotencyKeyTableMapper;
import com.pranay.order_payment_service.exceptions.IdempotencyKeyAlreadyExistsException;
import com.pranay.order_payment_service.models.IdempotencyKeyTable;
import com.pranay.order_payment_service.repository.IdempotencyTableRepository;
import com.pranay.order_payment_service.services.IdempotencyKeysService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IdempotencyKeysServiceImpl implements IdempotencyKeysService {

    private final IdempotencyTableRepository idempotencyTableRepository;
    private final IdempotencyKeyTableMapper idempotencyKeyTableMapper;

    @Override
    public IdempotencyKeyDto getIdempotencyKey(IdempotencyKeyDto idempotencyKeyDto) {

        IdempotencyKeyTable idempotencyKey = idempotencyTableRepository.findIdempotencyKeyTableByKey(idempotencyKeyDto.key());
        if (idempotencyKey.getKey() != null) {
            throw new IdempotencyKeyAlreadyExistsException("Idempotency key already exists");
        }
        return idempotencyKeyTableMapper.toDto(idempotencyKey);
    }

    @Override
    public PagedResponse<IdempotencyKeyDto> getAllIdempotencyKeys(Pageable pageable) {
        return null;
    }
}
