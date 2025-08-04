package com.exam.examapp.security.dto;

import jakarta.validation.constraints.Size;

public record RegisterRequest(@Size(min = 5, max = 50) String username,
                              @Size(min = 8) String password,
                              @Size(min = 6, max = 50) String email,
                              @Size(min = 12) String phoneNumber) {
}
