package com.exam.examapp.repository.question;

import com.exam.examapp.model.question.MatchQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchQuestionRepository extends JpaRepository<MatchQuestion, Long> {
}
