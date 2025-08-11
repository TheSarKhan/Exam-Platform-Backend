package com.exam.examapp.repository.question;

import com.exam.examapp.model.question.OpenEndedQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OpenEndedQuestionRepository extends JpaRepository<OpenEndedQuestion, Long> {
}
