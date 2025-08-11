package com.exam.examapp.model;


import com.exam.examapp.model.enums.AnswerStatus;
import com.exam.examapp.model.enums.ExamStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.Instant;
import java.util.Map;
import java.util.UUID;

@Getter
@Setter
@Entity
@Builder
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "students_exams")
public class StudentExam {
    @Id
    private UUID id;

    @JoinColumn(nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private User student;

    @JoinColumn(nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Exam exam;

    @Enumerated(EnumType.STRING)
    private ExamStatus status;

    private double score;

    @JdbcTypeCode(SqlTypes.JSON)
    private Map<Character, String> questionNumberToAnswerMap;

    @JdbcTypeCode(SqlTypes.JSON)
    @Enumerated(EnumType.STRING)
    private Map<Character, AnswerStatus> questionNumberToAnswerStatusMap;

    private Instant createdAt;

    private Instant updatedAt;

    @PrePersist
    void prePersist() {
        status= ExamStatus.ACTIVE;
        createdAt = updatedAt = Instant.now();
    }

    @PreUpdate
    void preUpdate() {
        updatedAt = Instant.now();
    }
}
