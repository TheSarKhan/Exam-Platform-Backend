package com.exam.examapp.model;


import com.exam.examapp.model.enums.ExamStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.Instant;
import java.util.Map;

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
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private User student;

    @JoinColumn(nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Exam exam;

    private ExamStatus status;

    private double score;

    @JdbcTypeCode(SqlTypes.JSON)
    private Map<Character, String> questionNumberToAnswerMap;

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
