package com.pranay.order_payment_service.services.serviceImpl;

import com.pranay.order_payment_service.DTO.IdempotencyKeyDto;
import com.pranay.order_payment_service.DTO.PagedResponse;
import com.pranay.order_payment_service.config.IdempotencyKeyTableMapper;
import com.pranay.order_payment_service.exceptions.IdempotencyKeyAlreadyExistsException;
import com.pranay.order_payment_service.exceptions.ResourceNotFoundException;
import com.pranay.order_payment_service.models.IdempotencyKeyTable;
import com.pranay.order_payment_service.repository.IdempotencyTableRepository;
import com.pranay.order_payment_service.services.IdempotencyKeysService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class IdempotencyKeysServiceImpl implements IdempotencyKeysService {

    private final IdempotencyTableRepository idempotencyTableRepository;
    private final IdempotencyKeyTableMapper idempotencyKeyTableMapper;

    @Override
    public Boolean getIdempotencyKey(IdempotencyKeyDto idempotencyKeyDto) {

        Boolean idempotencyKey = idempotencyTableRepository.existsByKey(idempotencyKeyDto.key());
        return !idempotencyKey;
    }

    @Override
    public PagedResponse<IdempotencyKeyDto> getAllIdempotencyKeys(Pageable pageable) {
        Page<IdempotencyKeyTable> pagedIdempotencyKeys = idempotencyTableRepository.findAll(pageable);
        PagedResponse<IdempotencyKeyDto> pagedResponse = idempotencyKeyTableMapper.toPagedResponse(pagedIdempotencyKeys);
        return pagedResponse;
    }

    @Override
    @Transactional
    public void recordInFlightKey(UUID key) {
        IdempotencyKeyTable idempotencyKey = idempotencyTableRepository.findIdempotencyKeyTableByKey(key);
        if (idempotencyKey == null) {
            IdempotencyKeyTable idempotencyKeyTable = new IdempotencyKeyTable();
            idempotencyKeyTable.setKey(key);
            idempotencyKeyTable.setResponseStatus(102); // 102 processing
            idempotencyTableRepository.save(idempotencyKeyTable);
        } else {
            throw new IdempotencyKeyAlreadyExistsException("The idempotency key already exits");
        }
    }

    @Override
    @Transactional
    public void finalizeKeyStatus(UUID key, int responseStatus) {
        IdempotencyKeyTable idempotencyKey = idempotencyTableRepository.findIdempotencyKeyTableByKey(key);
        if (idempotencyKey == null) {
            throw new ResourceNotFoundException("Idempotency key not found: " + key);
        }
        idempotencyKey.setResponseStatus(responseStatus);
        idempotencyTableRepository.save(idempotencyKey);
    }

    @Override
    public Optional<Integer> getCachedStatus(UUID key) {
        IdempotencyKeyTable idempotencyKey = idempotencyTableRepository.findIdempotencyKeyTableByKey(key);
        if (idempotencyKey == null) {
            return Optional.empty();
        }
        return Optional.ofNullable(idempotencyKey.getResponseStatus());
    }
}
