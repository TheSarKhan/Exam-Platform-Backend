package com.exam.examapp.security.service.impl;

import com.exam.examapp.exception.custom.ResourceNotFoundException;
import com.exam.examapp.model.User;
import com.exam.examapp.model.enums.Role;
import com.exam.examapp.repository.UserRepository;
import com.exam.examapp.security.dto.LoginRequest;
import com.exam.examapp.security.dto.RegisterRequest;
import com.exam.examapp.security.dto.TokenResponse;
import com.exam.examapp.security.service.interfaces.AuthService;
import com.exam.examapp.security.service.interfaces.EmailService;
import com.exam.examapp.service.interfaces.CacheService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.function.Supplier;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final String REFRESH_TOKEN_HEADER = "refresh_token_";

    private final String FORGET_PASSWORD_HEADER = "refresh_token_";

    private final String ACCESS_RESET_PASSWORD = "access_reset_password_";

    private final Supplier<ResourceNotFoundException> emailNotFoundExceptionSupplier = () -> {
        log.error("Email not found.");
        return new ResourceNotFoundException("Email not found.");
    };

    private final UserRepository userRepository;

    private final CacheService cacheService;

    private final JwtService jwtService;

    private final EmailService emailService;

    private final PasswordEncoder passwordEncoder;

    @Override
    public String registerAsTeacher(RegisterRequest request) {
        return register(request, Role.TEACHER);
    }

    @Override
    public String registerAsStudent(RegisterRequest request) {
        return register(request, Role.STUDENT);
    }

    private String register(RegisterRequest request, Role role) {
        log.info("Registering {} : {}", role.name(), request.username());
        if (isUserExist(request)) {
            log.error("{} already exists with this information : {}", role.name(), request);
            return role.name() + " already exists with this information.";
        }
        User user = User.builder()
                .email(request.email())
                .role(role)
                .password(passwordEncoder.encode(request.password()))
                .username(request.username())
                .phoneNumber(request.phoneNumber())
                .build();
        userRepository.save(user);
        return role.name() + " registered successfully.";
    }

    private boolean isUserExist(RegisterRequest request) {
        return userRepository.existsByUsername(request.username()) ||
                userRepository.existsByEmail(request.email()) ||
                userRepository.existsByPhoneNumber(request.phoneNumber());
    }

    @Override
    public TokenResponse login(LoginRequest request) {
        log.info("Logging in user: {}", request.username());
        User user = userRepository.findByUsername(request.username()).orElseThrow(() ->
        {
            log.error("User not found.");
            return new ResourceNotFoundException("User not found.");
        });
        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            log.error("Invalid username or password.");
            throw new IllegalArgumentException("Invalid username or password.");
        }
        if (!user.isActive() || user.isDeleted() ){
            log.error("User is not active or deleted.");
            throw new IllegalArgumentException("User is not active or deleted.");
        }
        String accessToken = jwtService.generateAccessToken(request.username());
        String refreshToken = jwtService.generateRefreshToken(request.username());

        log.info("User logged in successfully.");
        return new TokenResponse(accessToken, refreshToken);
    }

    @Override
    public String logout(String username) {
        log.info("Logging out user: {}", username);

        cacheService.deleteContent(REFRESH_TOKEN_HEADER, username);

        log.info("Cache deleted.");
        return "User logged out successfully.";
    }

    @Override
    public TokenResponse refresh(String refreshToken) {
        log.info("Refreshing token for refresh token: {}", refreshToken);
        String username = jwtService.extractUsername(refreshToken);
        if (username == null) {
            log.error("Invalid refresh token.");
            throw new IllegalArgumentException("Invalid refresh token.");
        }
        cacheService.deleteContent(REFRESH_TOKEN_HEADER, username);

        String accessToken = jwtService.generateAccessToken(username);
        String newRefreshToken = jwtService.generateRefreshToken(username);

        log.info("Token refreshed successfully.");
        return new TokenResponse(accessToken, newRefreshToken);
    }

    @Override
    public String forgetPassword(String email) {
        log.info("Forgetting password for email: {}", email);
        int randomCode = (int) (Math.random() * 900000) + 100000;
        userRepository.findByEmail(email).orElseThrow(emailNotFoundExceptionSupplier);
        String emailResponse =
                emailService.sendEmail(email, "Akademix Verification Code", "Verification Code : " + randomCode);
        cacheService.saveContent(FORGET_PASSWORD_HEADER, email, String.valueOf(randomCode), Long.valueOf(5 * 60 * 1000));
        log.info(emailResponse);
        return emailResponse;
    }

    @Override
    public String verifyEmailCode(String email, String code) {
        log.info("Verifying email code for email: {}", email);
        String redisCode = cacheService.getContent(FORGET_PASSWORD_HEADER, email);
        if (redisCode == null || !redisCode.equals(code)) {
            log.error("Invalid code. code : "+"salam"+" redisCode : "+redisCode);
            return "Invalid code.";
        }
        cacheService.deleteContent(FORGET_PASSWORD_HEADER, email);
        log.info("Code verified successfully.");
        UUID uuid = UUID.randomUUID();
        cacheService.saveContent(ACCESS_RESET_PASSWORD, email, uuid.toString(), (long) 5 * 60 * 1000);
        return uuid.toString();
    }

    @Override
    public String resetPassword(String email, String password, String uuid) {
        log.info("Resetting password for email: {}", email);
        String redisUuid = cacheService.getContent(ACCESS_RESET_PASSWORD, email);
        if (redisUuid == null || !redisUuid.equals(uuid)) {
            return "Please don't copy paste the link.";
        }
        cacheService.deleteContent(ACCESS_RESET_PASSWORD, email);
        User user = userRepository.findByEmail(email).orElseThrow(emailNotFoundExceptionSupplier);
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
        log.info("Password reset successfully.");
        return "Password reset successfully. Please login with new password.";
    }
}
