package com.example.javaspringboot.Submissions.Service;

import com.example.javaspringboot.Submissions.Model.SubmittedHangman;
import com.example.javaspringboot.Submissions.Model.SubmittedMatch;
import com.example.javaspringboot.Submissions.Repository.SubmittedMatchRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class SubmittedMatchService {
    private final SubmittedMatchRepo submittedMatchRepo;

    @Autowired
    public SubmittedMatchService(SubmittedMatchRepo SubmittedMatchRepository) {
        this.submittedMatchRepo = SubmittedMatchRepository;
    }

    public SubmittedMatch addSubmittedMatch(SubmittedMatch matchSubmitted) { return submittedMatchRepo.save(matchSubmitted); }

    public List<SubmittedMatch> findAll(){ return submittedMatchRepo.findAll(); }

    public SubmittedMatch updateSubmittedMatch(SubmittedMatch matchSubmitted){ return submittedMatchRepo.save(matchSubmitted); }

    //query method (auto generates method in spring back-backend)
    @Transactional
    public void deleteSubmittedMatch(Long id) { submittedMatchRepo.deleteSubmittedMatchById(id);}

    public SubmittedMatch findSubmittedMatchById(Long id)
    {
        SubmittedMatch find = submittedMatchRepo.findSubmittedMatchById(id);
        if (find != null){return find;}
        return null;
    }

    public List<SubmittedMatch> findAllByUserId(Long id) {
        return submittedMatchRepo.findAllByUserIdOrderByGeneratedDateDesc(id);
    }
}
