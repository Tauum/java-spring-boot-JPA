package com.example.javaspringboot.Repo.Submissions;

import com.example.javaspringboot.Model.Submissions.SubmittedSwipe;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SubmittedSwipeRepo extends JpaRepository<SubmittedSwipe, Long> {


    void deleteSubmittedQuizById(Long id);

    Optional<SubmittedSwipe> findSubmittedSwipeById(Long id);

    //    https://stackoverflow.com/questions/70701668/springboot-list-of-objects-with-child-entities-not-returned/70701882#70701882
    @EntityGraph(attributePaths = {"user", "swipe"})
    List<SubmittedSwipe> findAllByUserIdOrderByGeneratedDateDesc(Long id);

    void deleteSubmittedSwipeById(Long id);
}

