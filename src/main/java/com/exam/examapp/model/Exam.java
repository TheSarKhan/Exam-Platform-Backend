package com.exam.examapp.model;

import com.exam.examapp.model.enums.Difficulty;
import com.exam.examapp.model.question.Question;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
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
@Table(name = "exams")
public class Exam {
    @Id
    private UUID id;

    private String examTitle;

    private String examDescription;

    private Difficulty difficulty;

    @ManyToOne
    private Subject subject;

    private int durationInMinutes;

    private int classNumber;

    private BigDecimal cost;

    @ManyToMany
    private List<Topic> topic;

    private double rating;

    @JdbcTypeCode(SqlTypes.JSON)
    private Map<Long, Double> userIdToRatingMap;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Question> questions;

    @ManyToOne(fetch = FetchType.LAZY)
    private User teacher;

    private long averageCheckTimeInMinute;

    private boolean isDeleted;

    private Instant createdAt;

    private Instant updatedAt;

    private Instant deletedAt;

    @PrePersist
    void prePersist() {
        createdAt = updatedAt = Instant.now();
    }

    @PreUpdate
    void preUpdate() {
        updatedAt = Instant.now();
    }
}
