package com.example.javaspringboot.Repo.Submissions;

import com.example.javaspringboot.Model.Submissions.SubmittedQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SubmittedQuestionRepo extends JpaRepository<SubmittedQuestion, Long> {

    void deleteSubmittedQuestionById(Long id);

    Optional<SubmittedQuestion> findSubmittedQuestionById(Long id);
}