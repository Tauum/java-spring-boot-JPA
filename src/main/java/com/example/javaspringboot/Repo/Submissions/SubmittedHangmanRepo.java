package com.example.javaspringboot.Repo.Submissions;

import com.example.javaspringboot.Model.Submissions.SubmittedHangman;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SubmittedHangmanRepo extends JpaRepository<SubmittedHangman, Long> {

    void deleteSubmittedHangmanById(Long id);

    Optional<SubmittedHangman> findSubmittedHangmanById(Long id);

    //    https://stackoverflow.com/questions/70701668/springboot-list-of-objects-with-child-entities-not-returned/70701882#70701882
    @EntityGraph(attributePaths = {"user"})
    List<SubmittedHangman> findAllByUserIdOrderByGeneratedDateDesc(Long id);
}