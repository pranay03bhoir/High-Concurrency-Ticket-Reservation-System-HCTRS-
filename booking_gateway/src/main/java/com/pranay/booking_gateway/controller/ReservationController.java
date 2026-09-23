package com.pranay.booking_gateway.controller;

import com.pranay.booking_gateway.dto.ReservationRequest;
import com.pranay.booking_gateway.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/reserve")
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping("/")
    public ResponseEntity<String> reserveTicket(
            @RequestHeader("Idempotency-Key") String idempotencyKey,
            @RequestBody ReservationRequest request,
            Principal principal
    ) {

        String userId = principal.getName(); // Gives userId

        boolean result = reservationService.reserveTicket(
                request.eventId(),
                userId,
                idempotencyKey,
                request.quantity()
        );

        return result ? ResponseEntity.accepted().body("Reservation Pending")
                : ResponseEntity.status(HttpStatus.CONFLICT).body("Sold out");
    }


}
