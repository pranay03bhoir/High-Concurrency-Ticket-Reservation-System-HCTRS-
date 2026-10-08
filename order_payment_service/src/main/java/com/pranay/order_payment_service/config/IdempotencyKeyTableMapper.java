package com.pranay.order_payment_service.config;

import com.pranay.order_payment_service.DTO.IdempotencyKeyDto;
import com.pranay.order_payment_service.DTO.PagedResponse;
import com.pranay.order_payment_service.models.IdempotencyKeyTable;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring")
public interface IdempotencyKeyTableMapper {

    @Mapping(source = "id", target = "id", ignore = true)
    @Mapping(source = "createdAt", target = "createdAt", ignore = true)
    IdempotencyKeyDto toDto(IdempotencyKeyTable idempotencyKeyTable);

    @Mapping(source = "id", target = "id", ignore = true)
    @Mapping(source = "createdAt", target = "createdAt", ignore = true)
    IdempotencyKeyTable toEntity(IdempotencyKeyDto idempotencyKeyDto);

    PagedResponse<IdempotencyKeyDto> toPagedResponse(Page<IdempotencyKeyTable> idempotencyKeyTables);

}
