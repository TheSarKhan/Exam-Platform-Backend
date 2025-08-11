package com.exam.examapp.model.question;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Entity
@Builder
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "listening_questions")
public class ListeningQuestion {
    @Id
    private Long id;

    private String soundUrl;

    @OneToMany
    private List<Question> questions;
}
