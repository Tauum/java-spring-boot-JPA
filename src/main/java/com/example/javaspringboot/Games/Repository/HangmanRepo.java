package com.example.javaspringboot.Games.Repository;

import com.example.javaspringboot.Games.Model.Hangman;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HangmanRepo extends JpaRepository<Hangman, Long> {

    void deleteHangmanById(Long id);

    Hangman findHangmanById(Long id);

    Hangman findFirstByHiddenOrderByIdDesc(boolean hidden);

    List<Hangman> findAllByOrderByGeneratedDateDesc();

    List<Hangman> findAllByHiddenOrderByGeneratedDateDesc(boolean hidden);
}