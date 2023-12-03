package com.mysite.spring.security.jwt.dtos;

import lombok.Data;

// что нам нужно для формирования токена
@Data
public class JwtRequest {
    private String username;
    private String password;
}
