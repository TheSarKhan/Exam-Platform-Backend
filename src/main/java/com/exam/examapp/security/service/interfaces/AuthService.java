package com.exam.examapp.security.service.interfaces;

import com.exam.examapp.security.dto.LoginRequest;
import com.exam.examapp.security.dto.RegisterRequest;
import com.exam.examapp.security.dto.TokenResponse;

public interface AuthService {
    String registerAsTeacher(RegisterRequest request);

    String registerAsStudent(RegisterRequest request);

    TokenResponse login(LoginRequest request);

    String logout(String username);

    TokenResponse refresh(String refreshToken);

    String forgetPassword(String email);

    String verifyEmailCode(String email, String  code);

    String resetPassword(String email, String password, String uuid);
}