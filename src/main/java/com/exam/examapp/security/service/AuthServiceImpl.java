package com.exam.examapp.security.service;

import com.exam.examapp.model.User;
import com.exam.examapp.model.enums.Role;
import com.exam.examapp.repository.UserRepository;
import com.exam.examapp.security.dto.RegisterRequest;
import com.exam.examapp.security.dto.TokenResponse;
import com.exam.examapp.service.interfaces.CacheService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;

    private final CacheService cacheService;

    private final JwtService jwtService;

    private final PasswordEncoder passwordEncoder;

    @Override
    public String register(RegisterRequest request) {
        log.info("Registering user: {}", request.username());
        if (isUserExist(request)) {
            log.error("User already exists with this information : {}", request);
            return "User already exists with this information.";
        }
        User user = User.builder()
                .email(request.email())
                .role(Role.USER)
                .password(passwordEncoder.encode(request.password()))
                .username(request.username())
                .build();
        userRepository.save(user);
        return "User registered successfully";
    }

    @Override
    public TokenResponse login(String username, String password) {
        log.info("Logging in user: {}", username);
        User user = userRepository.findByUsername(username);
        if (user == null || !passwordEncoder.matches(password, user.getPassword())) {
            log.error("Invalid username or password.");
            throw new IllegalArgumentException("Invalid username or password.");
        }
        String accessToken = jwtService.generateAccessToken(username);
        String refreshToken = jwtService.generateRefreshToken(username);

        cacheService.saveRefreshToken(username, refreshToken);

        log.info("User logged in successfully.");
        return new TokenResponse(accessToken, refreshToken);
    }

    @Override
    public String logout(String username) {
        log.info("Logging out user: {}", username);

        cacheService.deleteRefreshToken(username);

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
        cacheService.deleteRefreshToken(username);

        String accessToken = jwtService.generateAccessToken(username);
        String newRefreshToken = jwtService.generateRefreshToken(username);

        cacheService.saveRefreshToken(username, newRefreshToken);

        log.info("Token refreshed successfully.");
        return new TokenResponse(accessToken, newRefreshToken);
    }

    private boolean isUserExist(RegisterRequest request) {
        return userRepository.existsByUsername(request.username()) ||
                userRepository.existsByEmail(request.email()) ||
                userRepository.existsByPhoneNumber(request.phoneNumber());
    }
}
