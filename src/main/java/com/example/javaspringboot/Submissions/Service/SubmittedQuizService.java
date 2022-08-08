package com.example.javaspringboot.Submissions.Service;

import com.example.javaspringboot.Submissions.Model.SubmittedPropagate;
import com.example.javaspringboot.Submissions.Model.SubmittedQuiz;
import com.example.javaspringboot.Submissions.Repository.SubmittedQuizRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class SubmittedQuizService {
    private final SubmittedQuizRepo submittedQuizRepo;

    @Autowired
    public SubmittedQuizService(SubmittedQuizRepo SubmittedQuizRepository) {
        this.submittedQuizRepo = SubmittedQuizRepository;
    }

    public SubmittedQuiz addSubmittedQuiz(SubmittedQuiz quizSubmitted) { return submittedQuizRepo.save(quizSubmitted); }

    public List<SubmittedQuiz> findAll(){ return submittedQuizRepo.findAll(); }

    public SubmittedQuiz updateSubmittedQuiz(SubmittedQuiz quizSubmitted){ return submittedQuizRepo.save(quizSubmitted); }

    //query method (auto generates method in spring back-backend)
    @Transactional
    public void deleteSubmittedQuiz(Long id) { submittedQuizRepo.deleteSubmittedQuizById(id);}

    public SubmittedQuiz findSubmittedQuizById(Long id)
    {
        SubmittedQuiz find = submittedQuizRepo.findSubmittedQuizById(id);
        if (find != null){return find;}
        return null;
    }

    public List<SubmittedQuiz> findAllByUserId(Long id) {
        return submittedQuizRepo.findAllByUserIdOrderByGeneratedDateDesc(id);
    }
}
