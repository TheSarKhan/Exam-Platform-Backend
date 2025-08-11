package com.exam.examapp.dto.response;

import com.exam.examapp.model.Subject;
import com.exam.examapp.model.enums.Difficulty;

public record ExamResponseSimple(String id,
                                 String examTitle,
                                 Subject subject,
                                 Difficulty difficulty,
                                 double rating) {
}
