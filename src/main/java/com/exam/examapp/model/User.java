package com.exam.examapp.model;

import com.exam.examapp.model.enums.Role;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@AllArgsConstructor
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {
    @Id
    UUID id;

    @Column(unique = true, nullable = false)
    String username;

    @Column(unique = true, nullable = false)
    String email;

    @Column(unique = true, nullable = false)
    String phoneNumber;

    String password;

    Role role;

    boolean isActive;

    boolean isVerified;

    boolean isDeleted;

    LocalDateTime createdAt;

    LocalDateTime updatedAt;

    @PrePersist
    void prePersist() {
        id = UUID.randomUUID();
        isActive = true;
        updatedAt = createdAt = LocalDateTime.now();
    }

    @PreUpdate
    void preUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
