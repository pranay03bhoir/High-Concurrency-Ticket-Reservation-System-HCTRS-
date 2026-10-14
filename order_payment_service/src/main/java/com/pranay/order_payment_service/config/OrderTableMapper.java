package com.pranay.order_payment_service.config;

import com.pranay.order_payment_service.DTO.OrderRequestDto;
import com.pranay.order_payment_service.DTO.OrderResponseDto;
import com.pranay.order_payment_service.DTO.PagedResponse;
import com.pranay.order_payment_service.models.Orders;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring")
public interface OrderTableMapper {

    OrderResponseDto toOrderResponseDto(Orders orders);

    Orders toOrderEntity(OrderRequestDto orderRequestDto);

    PagedResponse<OrderResponseDto> toPagedResponse(Page<Orders> orders);
}
