package com.exam.examapp.service.impl;

import com.exam.examapp.exception.custom.ResourceNotFoundException;
import com.exam.examapp.exception.custom.UserNotLoginException;
import com.exam.examapp.model.User;
import com.exam.examapp.repository.UserRepository;
import com.exam.examapp.security.model.CustomUserDetails;
import com.exam.examapp.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.function.Supplier;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public Supplier<ResourceNotFoundException> resourceNotFoundExceptionSupplier = () -> {
        log.error("User not found.");
        return new ResourceNotFoundException("User not found.");
    };

    @Override
    public User getByUsername(String username) {
        log.info("Getting user by username: {}", username);
        return userRepository.findByUsername(username).orElseThrow(resourceNotFoundExceptionSupplier);
    }

    @Override
    public User getCurrentUser() {
        log.info("Getting current user.");
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();
        if (username.equals("anonymousUser")) {
            log.error("User not login.");
            throw new UserNotLoginException("User not login.");
        }
        return getByUsername(username);
    }
}
