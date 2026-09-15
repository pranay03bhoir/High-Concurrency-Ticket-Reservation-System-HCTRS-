package com.pranay.inventory_service.dto;

import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ErrorResponse {

    private LocalDateTime timeStamp;
    private String responseUrl;
    private int status;
    private String error;
    private String message;

    public ErrorResponse(String responseUrl, int status, String error, String message) {
        this.timeStamp = LocalDateTime.now();
        this.responseUrl = responseUrl;
        this.status = status;
        this.error = error;
        this.message = message;
    }
}
