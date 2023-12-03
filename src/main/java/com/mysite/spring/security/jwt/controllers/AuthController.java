package com.mysite.spring.security.jwt.controllers;

import com.mysite.spring.security.jwt.dtos.JwtRequest;
import com.mysite.spring.security.jwt.dtos.RegistrationUserDto;
import com.mysite.spring.security.jwt.services.AuthService;
import com.mysite.spring.security.jwt.services.UserService;
import com.mysite.spring.security.jwt.utils.JwtTokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

// контроллер для выдачи токенов
@RestController
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    private final JwtTokenUtils jwtTokenUtils;
    private final AuthService authService;

    // чтобы не проверять вручную, что по пришедшим логину и паролю пользователь существует
    private final AuthenticationManager authenticationManager;


    // формирование токена по присланным данным
    @PostMapping("/auth")
    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest authRequest) { // ? - для того, что может вернуться либо ошибка, либо успешный результат
        return authService.createAuthToken(authRequest);
    }

    // регистрация
    @PostMapping("/registration")
    public ResponseEntity<?> createNewUser(@RequestBody RegistrationUserDto registrationUserDto) {
        return authService.createNewUser(registrationUserDto);
    }
}
