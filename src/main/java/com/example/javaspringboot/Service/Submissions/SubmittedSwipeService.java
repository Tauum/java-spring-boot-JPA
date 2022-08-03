package com.example.javaspringboot.Service.Submissions;

import com.example.javaspringboot.Model.Submissions.SubmittedSwipe;
import com.example.javaspringboot.Repo.Submissions.SubmittedSwipeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class SubmittedSwipeService {
    private final SubmittedSwipeRepo submittedSwipeRepo;

    @Autowired
    public SubmittedSwipeService(SubmittedSwipeRepo SubmittedSwipeRepository) {
        this.submittedSwipeRepo = SubmittedSwipeRepository;
    }

    public SubmittedSwipe addSubmittedSwipe(SubmittedSwipe SwipeSubmitted) { return submittedSwipeRepo.save(SwipeSubmitted); }

    public List<SubmittedSwipe> findAll(){ return submittedSwipeRepo.findAll(); }

    public SubmittedSwipe updateSubmittedSwipe(SubmittedSwipe SwipeSubmitted){ return submittedSwipeRepo.save(SwipeSubmitted); }

    //query method (auto generates method in spring back-backend)
    @Transactional
    public void deleteSubmittedSwipe(Long id) { submittedSwipeRepo.deleteSubmittedSwipeById(id);}

    public SubmittedSwipe findSubmittedSwipeById(Long id)
    {
        return submittedSwipeRepo.findSubmittedSwipeById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entity not foud with id: " + id));
    }

    public List<SubmittedSwipe> findAllByUserId(Long id) {
        return submittedSwipeRepo.findAllByUserIdOrderByGeneratedDateDesc(id);
    }
}
