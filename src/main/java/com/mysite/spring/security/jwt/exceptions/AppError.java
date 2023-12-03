package com.mysite.spring.security.jwt.exceptions;

import lombok.Data;

import java.util.Date;

// DTO для описания ошибок
@Data
public class AppError {
    private int status;
    private String message;
    private Date timestamp;

    public AppError(int status, String message) {
        this.status = status;
        this.message = message;
        this.timestamp = new Date();
    }
}
