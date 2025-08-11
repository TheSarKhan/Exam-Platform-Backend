package com.exam.examapp.model.question;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

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
@Table(name = "match_questions")
public class MatchQuestion {
    @Id
    private UUID id;

    @Column(nullable = false)
    @JdbcTypeCode(SqlTypes.JSON)
    private Map<Character, String> numberToContentMap;

    @Column(nullable = false)
    @JdbcTypeCode(SqlTypes.JSON)
    private Map<Character, Boolean> numberToIsPictureMap;

    @Column(nullable = false)
    @JdbcTypeCode(SqlTypes.JSON)
    private Map<Character, String> variantToContentMap;

    @Column(nullable = false)
    @JdbcTypeCode(SqlTypes.JSON)
    private Map<Character, Boolean> variantToIsPictureMap;

    @Column(nullable = false)
    @JdbcTypeCode(SqlTypes.JSON)
    private Map<Character, List<Character>> numberToCorrectVariantsMap;
}
