package com.pranay.order_payment_service.exceptions;

public class IdempotencyKeyAlreadyExistsException extends RuntimeException {

    public IdempotencyKeyAlreadyExistsException(String message) {
        super(message);
    }

    public IdempotencyKeyAlreadyExistsException() {
    }
}
