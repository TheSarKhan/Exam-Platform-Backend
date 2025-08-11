package com.exam.examapp.model.question;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

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
@Table(name = "single_choice_questions")
public class SingleChoiceQuestion {
    @Id
    private UUID id;

    @Column(nullable = false)
    @JdbcTypeCode(SqlTypes.JSON)
    private Map<Character, String> variantToContentMap;

    @Column(nullable = false)
    @JdbcTypeCode(SqlTypes.JSON)
    private Map<Character, Boolean> variantToIsPictureMap;

    @Column(nullable = false)
    private char correctVariant;
}
