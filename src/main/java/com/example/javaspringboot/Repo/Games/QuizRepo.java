package com.example.javaspringboot.Repo.Games;

import com.example.javaspringboot.Model.Games.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface QuizRepo extends JpaRepository<Quiz, Long> {

    void deleteQuizById(Long id);

    Optional<Quiz> findQuizById(Long id);

    List<Quiz> findAllByOrderByGeneratedDateDesc();

    Quiz findFirstByOrderByIdDesc();

    List<Quiz> findAllByHiddenOrderByGeneratedDateDesc(boolean hidden);

    Quiz findFirstByHiddenOrderByIdDesc(boolean hidden);
}
