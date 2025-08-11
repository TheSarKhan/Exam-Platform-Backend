package com.exam.examapp.repository.question;

import com.exam.examapp.model.question.TextBasedQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TextBasedQuestionRepository extends JpaRepository<TextBasedQuestion, UUID> {
}
