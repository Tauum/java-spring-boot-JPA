package com.example.javaspringboot.Submissions.Service;

import com.example.javaspringboot.Games.Model.Swipe;
import com.example.javaspringboot.Submissions.Model.SubmittedHangman;
import com.example.javaspringboot.Submissions.Repository.SubmittedHangmanRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class SubmittedHangmanService {
    private final SubmittedHangmanRepo submittedHangmanRepo;
    @Autowired
    public SubmittedHangmanService(SubmittedHangmanRepo hangmanSubmittedRepo) {
        this.submittedHangmanRepo = hangmanSubmittedRepo;
    }

    public List<SubmittedHangman> findAll() {
        return submittedHangmanRepo.findAll();
    }

    public SubmittedHangman findSubmittedHangmanById(Long id) {
        SubmittedHangman find = submittedHangmanRepo.findSubmittedHangmanById(id);
        if (find != null){return find;}
        return null;
    }

    public SubmittedHangman addSubmittedHangman(SubmittedHangman hangmanSubmitted) {
        return submittedHangmanRepo.save(hangmanSubmitted);
    }

    public SubmittedHangman updateSubmittedHangman(SubmittedHangman hangmanSubmitted) {
        return submittedHangmanRepo.save(hangmanSubmitted);
    }
    @Transactional
    public void deleteSubmittedHangman(Long id) {
        submittedHangmanRepo.deleteSubmittedHangmanById(id);
    }

    public List<SubmittedHangman> findAllByUserId(Long id) {
        return submittedHangmanRepo.findAllByUserIdOrderByGeneratedDateDesc(id);
    }
}

