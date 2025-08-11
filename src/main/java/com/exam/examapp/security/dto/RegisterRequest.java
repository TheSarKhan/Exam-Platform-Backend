package com.exam.examapp.security.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterRequest(@NotBlank @Size(min = 5, max = 50) String username,
                              @NotBlank @Size(min = 8) String password,
                              @NotBlank @Size(min = 6, max = 50) String email,
                              @NotBlank @Size(min = 12) String phoneNumber) {
}
