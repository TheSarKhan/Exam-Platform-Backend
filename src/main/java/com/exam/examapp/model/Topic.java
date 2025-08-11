package com.exam.examapp.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Getter
@Setter
@Entity
@Builder
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@Table(name = "topics")
@RequiredArgsConstructor
public class Topic {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    private Subject subject;

    private boolean isActive;

    private boolean isDeleted;

    private Instant createdAt;

    private Instant updatedAt;

    @PrePersist
    void prePersist() {
        isActive = true;
        createdAt = updatedAt = Instant.now();
    }

    @PreUpdate
    void preUpdate() {
        updatedAt = Instant.now();
    }
}
