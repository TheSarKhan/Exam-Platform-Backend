package com.exam.examapp.repository.question;

import com.exam.examapp.model.question.ListeningQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ListeningQuestionRepository extends JpaRepository<ListeningQuestion, Long> {
}
