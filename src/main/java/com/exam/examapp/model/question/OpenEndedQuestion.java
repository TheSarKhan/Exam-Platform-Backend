package com.exam.examapp.model.question;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "open_ended_questions")
public class OpenEndedQuestion {
    @Id
    private Long id;

    @Column(nullable = false)
    private boolean isAuto;

    @Column(nullable = false)
    private String answer;
}
