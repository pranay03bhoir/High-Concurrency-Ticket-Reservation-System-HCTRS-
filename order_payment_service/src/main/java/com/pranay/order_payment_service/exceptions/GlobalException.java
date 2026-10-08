package com.pranay.order_payment_service.exceptions;

import com.pranay.order_payment_service.DTO.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(IdempotencyKeyAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> IdempotencyKeyExceptionHandler(IdempotencyKeyAlreadyExistsException e, HttpServletRequest request) {
        return new ResponseEntity<>(new ErrorResponse(
                request.getRequestURI(),
                HttpStatus.BAD_REQUEST.toString(),
                HttpStatus.BAD_REQUEST.value(),
                e.getMessage()),
                HttpStatus.BAD_REQUEST);
    }

}
