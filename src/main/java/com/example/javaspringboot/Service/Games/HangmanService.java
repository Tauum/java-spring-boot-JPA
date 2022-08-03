package com.example.javaspringboot.Service.Games;

import com.example.javaspringboot.Model.Games.Hangman;
import com.example.javaspringboot.Repo.Games.HangmanRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class HangmanService {
    private final HangmanRepo hangmanRepo;
    @Autowired
    public HangmanService(HangmanRepo hangmanRepo) {
        this.hangmanRepo = hangmanRepo;
    }

    public List<Hangman> findAll() {
        return hangmanRepo.findAll();
    }

    public Hangman findHangmanById(Long id) {
        return hangmanRepo.findHangmanById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entity not foud with id: " + id));
    }

    public Hangman addHangman(Hangman hangman) {
        return hangmanRepo.save(hangman);
    }

    public Hangman updateHangman(Hangman hangman) {
        return hangmanRepo.save(hangman);
    }
    @Transactional
    public void deleteHangman(Long id) {
        hangmanRepo.deleteHangmanById(id);
    }

    public Hangman findHanganOrderByGeneratedDateDesc() {
        return hangmanRepo.findFirstByHiddenOrderByIdDesc(false);
    }

    public List<Hangman> findAllOrderByGeneratedDateDesc() {
        return hangmanRepo.findAllByOrderByGeneratedDateDesc();
    }

    public List<Hangman> findAllOrderByGeneratedDateDescAndNotHidden() {
        return hangmanRepo.findAllByHiddenOrderByGeneratedDateDesc(false);
    }
}

