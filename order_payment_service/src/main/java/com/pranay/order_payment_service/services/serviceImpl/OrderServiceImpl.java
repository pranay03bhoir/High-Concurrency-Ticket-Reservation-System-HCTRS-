package com.pranay.order_payment_service.services.serviceImpl;

import com.pranay.order_payment_service.DTO.OrderRequestDto;
import com.pranay.order_payment_service.DTO.OrderResponseDto;
import com.pranay.order_payment_service.DTO.PagedResponse;
import com.pranay.order_payment_service.config.OrderTableMapper;
import com.pranay.order_payment_service.exceptions.ResourceNotFoundException;
import com.pranay.order_payment_service.models.Orders;
import com.pranay.order_payment_service.models.Status;
import com.pranay.order_payment_service.repository.OrdersRepository;
import com.pranay.order_payment_service.services.OrderService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrdersRepository ordersRepository;
    private final OrderTableMapper orderTableMapper;

    @Override
    @Transactional
    public OrderResponseDto createOrder(OrderRequestDto orderRequestDto) {
        Orders createdOrder = orderTableMapper.toOrderEntity(orderRequestDto);
        Orders savedOrder = ordersRepository.save(createdOrder);
        return orderTableMapper.toOrderResponseDto(savedOrder);
    }

    @Override
    @Transactional
    public OrderResponseDto markOrderComplete(UUID orderId) {
        Orders order = ordersRepository.findById(orderId).orElseThrow(
                () -> new ResourceNotFoundException("Order not found for orderId: " + orderId)
        );
        order.setStatus(Status.COMPLETED);
        Orders savedOrder = ordersRepository.save(order);
        return orderTableMapper.toOrderResponseDto(savedOrder);
    }

    @Override
    @Transactional
    public OrderResponseDto markOrderFailed(UUID orderId) {
        Orders order = ordersRepository.findById(orderId).orElseThrow(
                () -> new ResourceNotFoundException("Order not found for orderId: " + orderId)
        );
        order.setStatus(Status.FAILED);
        Orders savedOrder = ordersRepository.save(order);
        return orderTableMapper.toOrderResponseDto(savedOrder);
    }

    @Override
    public PagedResponse<OrderResponseDto> getOrders(Pageable pageable) {
        Page<Orders> orderPage = ordersRepository.findAll(pageable);
        if (orderPage.getContent().isEmpty()) {
            throw new ResourceNotFoundException("No orders created yet!!");
        }
        return orderTableMapper.toPagedResponse(orderPage);
    }
}
