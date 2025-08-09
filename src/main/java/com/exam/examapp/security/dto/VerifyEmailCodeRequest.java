package com.exam.examapp.security.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public record VerifyEmailCodeRequest(@Email String email, @Size(min = 6, max = 6) String code) {
}
