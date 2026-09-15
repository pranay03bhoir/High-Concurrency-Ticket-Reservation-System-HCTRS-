package com.pranay.inventory_service.exceptions;

import com.pranay.inventory_service.dto.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException e, HttpServletRequest request) {
        String requestPath = request.getRequestURI();
        ErrorResponse errorBody = new ErrorResponse(
                requestPath,
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.name(),
                e.getMessage()
        );

        return new ResponseEntity<>(errorBody, HttpStatus.NOT_FOUND);
    }
}
