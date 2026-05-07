package com.bookmyshow.controller;

import com.bookmyshow.dto.authResponseDto;
import com.bookmyshow.dto.loginRequestDto;
import com.bookmyshow.dto.registerRequestDto;
import com.bookmyshow.service.authService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class authController {

    private final authService authService;

    public authController(authService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public authResponseDto register(@Valid @RequestBody registerRequestDto request) {
        return authService.register(request);
    }

    @PostMapping("/login")
    public authResponseDto login(@Valid @RequestBody loginRequestDto request) {
        return authService.login(request);
    }
}
