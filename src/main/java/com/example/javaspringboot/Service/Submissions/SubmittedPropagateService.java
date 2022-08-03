package com.example.javaspringboot.Service.Submissions;

import com.example.javaspringboot.Model.Submissions.SubmittedPropagate;
import com.example.javaspringboot.Repo.Submissions.SubmittedPropagateRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class SubmittedPropagateService {
    private final SubmittedPropagateRepo submittedPropagateRepo;

    @Autowired
    public SubmittedPropagateService(SubmittedPropagateRepo SubmittedPropagateRepository) {
        this.submittedPropagateRepo = SubmittedPropagateRepository;
    }

    public SubmittedPropagate addSubmittedPropagate(SubmittedPropagate PropagateSubmitted) { return submittedPropagateRepo.save(PropagateSubmitted); }

    public List<SubmittedPropagate> findAll(){ return submittedPropagateRepo.findAll(); }

    public SubmittedPropagate updateSubmittedPropagate(SubmittedPropagate PropagateSubmitted){ return submittedPropagateRepo.save(PropagateSubmitted); }

    //query method (auto generates method in spring back-backend)
    @Transactional
    public void deleteSubmittedPropagate(Long id) { submittedPropagateRepo.deleteSubmittedPropagateById(id);}

    public SubmittedPropagate findSubmittedPropagateById(Long id)
    {
        return submittedPropagateRepo.findSubmittedPropagateById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entity not foud with id: " + id));
    }

    public List<SubmittedPropagate> findAllByUserId(Long id) {
        return submittedPropagateRepo.findAllByUserIdOrderByGeneratedDateDesc(id);
    }
}
