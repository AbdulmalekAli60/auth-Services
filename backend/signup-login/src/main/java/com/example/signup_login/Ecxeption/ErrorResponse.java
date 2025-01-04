package com.example.signup_login.Ecxeption;

import java.time.LocalDateTime;

public class ErrorResponse {
    private String message;
    private String details;
    private LocalDateTime localDateTime;

    public ErrorResponse(String message, String details) {
        this.message = message;
        this.localDateTime = LocalDateTime.now();
        this.details = details;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public String getDetails() {
        return details;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
