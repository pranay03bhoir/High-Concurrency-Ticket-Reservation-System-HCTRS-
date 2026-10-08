package com.pranay.order_payment_service.DTO;

import java.time.Instant;
import java.util.Objects;

public final class ErrorResponse {
    private final String path;
    private final String status;
    private final Integer statusCode;
    private final String message;
    private final Instant timeStamp = Instant.now();

    public ErrorResponse(
            String path,
            String status,
            Integer statusCode,
            String message
    ) {
        this.path = path;
        this.status = status;
        this.statusCode = statusCode;
        this.message = message;
    }

    public String path() {
        return path;
    }

    public String status() {
        return status;
    }

    public Integer statusCode() {
        return statusCode;
    }

    public String message() {
        return message;
    }

    public Instant timeStamp() {
        return timeStamp;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (ErrorResponse) obj;
        return Objects.equals(this.path, that.path) &&
                Objects.equals(this.status, that.status) &&
                Objects.equals(this.statusCode, that.statusCode) &&
                Objects.equals(this.message, that.message) &&
                Objects.equals(this.timeStamp, that.timeStamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(path, status, statusCode, message, timeStamp);
    }

    @Override
    public String toString() {
        return "ErrorResponse[" +
                "path=" + path + ", " +
                "status=" + status + ", " +
                "statusCode=" + statusCode + ", " +
                "message=" + message + ", " +
                "timeStamp=" + timeStamp + ']';
    }

}
