package com.pranay.booking_gateway.service;

import com.pranay.booking_gateway.dto.ReservationEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Collections;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {
    private final DefaultRedisScript<Long> reservationScript;
    private final StringRedisTemplate stringRedisTemplate;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    private static final String TOPIC = "reservation.pending";

    @Override
    public Boolean reserveTicket(String eventId, String userId, String idempotencyKey, int quantity) {

        String stockKey = "event:" + eventId.trim() + ":tickets_available";
        // 1. Execute Lua Script atomically inside Redis
        Long result = stringRedisTemplate.execute(
                reservationScript,                   // Lua Script
                Collections.singletonList(stockKey), // KEYS[1]
                String.valueOf(quantity)             // ARGV[1]
        );

        if (result != 1L) {
            return false;
        }

        // 2. Temporarily hold reserve the event for 10-minutes
        String reservationId = UUID.randomUUID().toString();
        String holdKey = "reservation:" + reservationId;
        stringRedisTemplate.opsForValue().set(holdKey, userId, Duration.ofMinutes(10));

        ReservationEvent reservationEvent = new ReservationEvent(
                reservationId,
                eventId,
                userId,
                System.currentTimeMillis()
        );

        kafkaTemplate.send(TOPIC, idempotencyKey, reservationEvent);

        return true;
    }
}
