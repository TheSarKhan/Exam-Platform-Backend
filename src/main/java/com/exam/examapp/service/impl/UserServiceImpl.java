package com.exam.examapp.service.impl;

import com.exam.examapp.model.User;
import com.exam.examapp.repository.UserRepository;
import com.exam.examapp.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public User getByUsername(String username) {
        log.info("Getting user by username: {}", username);
        return userRepository.findByUsername(username);
    }
}
