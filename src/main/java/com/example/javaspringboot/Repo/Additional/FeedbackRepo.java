package com.example.javaspringboot.Repo.Additional;

import com.example.javaspringboot.Model.Additional.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FeedbackRepo extends JpaRepository<Feedback, Long> {

    Optional<Feedback> findFeedbackById(Long id);

    void deleteFeedbackById(Long id);

    List<Feedback> findAllByOrderByGeneratedDate();
}

