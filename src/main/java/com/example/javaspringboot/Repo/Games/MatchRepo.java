package com.example.javaspringboot.Repo.Games;

import com.example.javaspringboot.Model.Games.Match;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MatchRepo extends JpaRepository<Match, Long> {

    void deleteMatchById(Long id);

    Optional<Match> findMatchById(Long id);

    List<Match> findAllByOrderByGeneratedDateDesc();

    List<Match> findAllByHiddenOrderByGeneratedDateDesc(boolean hidden);

    Match findFirstByHiddenOrderByIdDesc(boolean hidden);
}
