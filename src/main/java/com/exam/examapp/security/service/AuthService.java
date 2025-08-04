package com.exam.examapp.security.service;

import com.exam.examapp.security.dto.RegisterRequest;
import com.exam.examapp.security.dto.TokenResponse;

public interface AuthService {
    String register(RegisterRequest request);

    TokenResponse login(String username, String password);

    String logout(String username);

    TokenResponse refresh(String refreshToken);
}