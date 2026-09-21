package com.pranay.booking_gateway.service;

import org.springframework.stereotype.Service;

@Service
public interface ReservationService {

    Boolean reserveTicket(String eventId, String userId, String idempotencyKey, int quantity);

}
