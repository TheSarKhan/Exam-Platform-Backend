package com.exam.examapp.security.controller;

import com.exam.examapp.security.dto.RegisterRequest;
import com.exam.examapp.security.dto.TokenResponse;
import com.exam.examapp.security.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication", description = "Endpoints for user authentication operations")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    @Operation(summary = "Register a new user",
            description = "Creates a new user account and returns a success message")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    @Operation(summary = "Login a user",
            description = "Authenticates the user and returns an access and refresh token")
    public ResponseEntity<TokenResponse> login(String username, String password) {
        return ResponseEntity.ok(authService.login(username, password));
    }

    @PostMapping("/logout")
    @Operation(summary = "Logout a user",
            description = "Logs out the user and invalidates the session")
    public ResponseEntity<String> logout(String username) {
        return ResponseEntity.ok(authService.logout(username));
    }

    @PostMapping("/refresh")
    @Operation(summary = "Refresh access token",
            description = "Generates a new access token using the refresh token")
    public ResponseEntity<TokenResponse> refresh(String refreshToken) {
        return ResponseEntity.ok(authService.refresh(refreshToken));
    }
}
