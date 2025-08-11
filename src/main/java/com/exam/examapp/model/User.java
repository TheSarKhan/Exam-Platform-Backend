package com.exam.examapp.model;

import com.exam.examapp.model.enums.Role;
import com.exam.examapp.security.model.CustomUserDetails;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Builder
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@Table(name = "users")
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

    String profilePictureUrl;

    Role role;

    boolean isActive;

    boolean isDeleted;

    boolean isEnabled;

    boolean isAccountNonExpired;

    boolean isAccountNonLocked;

    boolean isCredentialsNonExpired;

    LocalDateTime createdAt;

    LocalDateTime updatedAt;

    @PrePersist
    void prePersist() {
        id = UUID.randomUUID();
        isActive =
                isAccountNonExpired =
                        isAccountNonLocked =
                                isCredentialsNonExpired =
                                        isEnabled =
                                                true;
        updatedAt = createdAt = LocalDateTime.now();
    }

    @PreUpdate
    void preUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public CustomUserDetails getCustomUserDetails() {
        return new CustomUserDetails(this);
    }
}
