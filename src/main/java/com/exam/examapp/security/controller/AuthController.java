package com.exam.examapp.security.controller;

import com.exam.examapp.security.dto.LoginRequest;
import com.exam.examapp.security.dto.RegisterRequest;
import com.exam.examapp.security.dto.TokenResponse;
import com.exam.examapp.security.service.interfaces.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication", description = "Endpoints for user authentication operations")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register-teacher")
    @Operation(summary = "Register a new teacher",
            description = "Creates a new teacher account and returns a success message")
    public ResponseEntity<String> registerAsTeacher(@RequestBody @Valid RegisterRequest request) {
        return ResponseEntity.ok(authService.registerAsTeacher(request));
    }

    @PostMapping("/register-student")
    @Operation(summary = "Register a new student",
            description = "Creates a new student account and returns a success message")
    public ResponseEntity<String> registerAsStudent(@RequestBody @Valid RegisterRequest request) {
        return ResponseEntity.ok(authService.registerAsStudent(request));
    }

    @PostMapping("/login")
    @Operation(summary = "Login a user",
            description = "Authenticates the user and returns an access and refresh token")
    public ResponseEntity<TokenResponse> login(@RequestBody @Valid LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping("/logout")
    @Operation(summary = "Logout a user",
            description = "Logs out the user and invalidates the session")
    public ResponseEntity<String> logout(@RequestParam String username) {
        return ResponseEntity.ok(authService.logout(username));
    }

    @PostMapping("/refresh")
    @Operation(summary = "Refresh access token",
            description = "Generates a new access token using the refresh token")
    public ResponseEntity<TokenResponse> refresh(@RequestParam String refreshToken) {
        return ResponseEntity.ok(authService.refresh(refreshToken));
    }

    @PostMapping("/forget-password")
    public ResponseEntity<String> forgetPassword(@RequestParam @Email String email) {
       return ResponseEntity.ok(authService.forgetPassword(email));
    }

    @PostMapping("/verify-email-code")
    public ResponseEntity<String> verifyEmailCode(@RequestParam @Email String email, @RequestParam String code) {
        return ResponseEntity.ok(authService.verifyEmailCode(email, code));
    }

    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestParam @Email String email, @RequestParam String password, @RequestParam String uuid) {
        return ResponseEntity.ok(authService.resetPassword(email, password, uuid));
    }
}
