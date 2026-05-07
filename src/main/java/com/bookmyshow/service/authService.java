package com.bookmyshow.service;

import com.bookmyshow.dto.authResponseDto;
import com.bookmyshow.dto.loginRequestDto;
import com.bookmyshow.dto.registerRequestDto;
import com.bookmyshow.entity.userEntity;
import com.bookmyshow.entity.userRoleEnum;
import com.bookmyshow.repository.userRepository;
import com.bookmyshow.security.jwtService;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

@Service
public class authService {

    private final userRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final jwtService jwtService;

    public authService(userRepository userRepository, PasswordEncoder passwordEncoder,
                       AuthenticationManager authenticationManager, jwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    public authResponseDto register(registerRequestDto request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already registered");
        }

        userEntity user = new userEntity();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(userRoleEnum.USER);
        user.setIsActive(true);
        userEntity savedUser = userRepository.save(user);

        User securityUser = new User(savedUser.getEmail(), savedUser.getPassword(), java.util.List.of());
        String token = jwtService.generateToken(securityUser, Map.of("role", savedUser.getRole().name(), "userId", savedUser.getId()));

        return buildAuthResponse(token, savedUser);
    }

    public authResponseDto login(loginRequestDto request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        userEntity user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        User securityUser = new User(user.getEmail(), user.getPassword(), java.util.List.of());
        String token = jwtService.generateToken(securityUser, Map.of("role", user.getRole().name(), "userId", user.getId()));

        return buildAuthResponse(token, user);
    }

    private authResponseDto buildAuthResponse(String token, userEntity user) {
        authResponseDto response = new authResponseDto();
        response.setToken(token);
        response.setTokenType("Bearer");
        response.setUserId(user.getId());
        response.setEmail(user.getEmail());
        response.setRole(user.getRole().name());
        return response;
    }
}
