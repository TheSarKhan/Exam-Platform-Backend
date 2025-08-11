package com.exam.examapp.model.question;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Builder
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "text_based_questions")
public class TextBasedQuestion {
    @Id
    private UUID id;

    private int questionCount;

    @OneToMany
    private List<Question> questions;
}
