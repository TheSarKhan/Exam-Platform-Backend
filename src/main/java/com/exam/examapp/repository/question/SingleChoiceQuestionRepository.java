package com.exam.examapp.repository.question;

import com.exam.examapp.model.question.SingleChoiceQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SingleChoiceQuestionRepository extends JpaRepository<SingleChoiceQuestion, UUID> {
}
