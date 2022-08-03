package com.example.javaspringboot.Service.Submissions;


import com.example.javaspringboot.Model.Submissions.SubmittedQuestion;
import com.example.javaspringboot.Repo.Submissions.SubmittedQuestionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class SubmittedQuestionService {
    private final SubmittedQuestionRepo submittedQuestionRepo;

    @Autowired
    public SubmittedQuestionService(SubmittedQuestionRepo submittedQuestionRepository) {
        this.submittedQuestionRepo = submittedQuestionRepository;
    }

    public SubmittedQuestion addSubmittedQuestion(SubmittedQuestion submittedQuestion) { return submittedQuestionRepo.save(submittedQuestion); }

    public List<SubmittedQuestion> findAll(){ return submittedQuestionRepo.findAll(); }

    public SubmittedQuestion updateSubmittedQuestion(SubmittedQuestion submittedQuestion){ return submittedQuestionRepo.save(submittedQuestion); }

    //query method (auto generates method in spring back-backend)
    public void deleteSubmittedQuestion(Long id) { submittedQuestionRepo.deleteSubmittedQuestionById(id);}

    public SubmittedQuestion findSubmittedQuestionById(Long id)
    {
        return submittedQuestionRepo.findSubmittedQuestionById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entity not foud with id: " + id));
    }
}