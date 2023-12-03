package com.mysite.spring.security.jwt.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

// возвращаемый токен
@Data
@AllArgsConstructor
public class JwtResponse {
    private String token;
}
