package com.pranay.booking_gateway.controller;

// Only for dev use

import com.pranay.booking_gateway.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/dev")
public class GenerateUserAuthTokenController {

    private final JwtUtils jwtUtils;

    @GetMapping("/generate-token")
    public ResponseEntity<Map<String, String>> generateUserAuthToken(@RequestParam(required = false) String userId) {
        String effectiveUserId = (userId != null) ? userId : UUID.randomUUID().toString();

        String token = jwtUtils.generateToken(effectiveUserId);
        return ResponseEntity.ok(Map.of("userId", effectiveUserId, "token", token));
    }
}
