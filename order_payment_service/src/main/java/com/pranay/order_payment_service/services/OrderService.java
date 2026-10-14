package com.pranay.order_payment_service.services;

import com.pranay.order_payment_service.DTO.OrderRequestDto;
import com.pranay.order_payment_service.DTO.OrderResponseDto;
import com.pranay.order_payment_service.DTO.PagedResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface OrderService {

    OrderResponseDto createOrder(OrderRequestDto orderRequestDto);

    OrderResponseDto markOrderComplete(UUID orderId);

    OrderResponseDto markOrderFailed(UUID orderId);

    PagedResponse<OrderResponseDto> getOrders(Pageable pageable);

}
