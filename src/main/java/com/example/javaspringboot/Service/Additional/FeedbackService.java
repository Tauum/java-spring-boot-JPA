package com.example.javaspringboot.Service.Additional;


import com.example.javaspringboot.Model.Additional.Feedback;
import com.example.javaspringboot.Repo.Additional.FeedbackRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class FeedbackService {
    private final FeedbackRepo feedbackRepo;

    @Autowired
    public FeedbackService(FeedbackRepo feedbackRepository) {
        this.feedbackRepo = feedbackRepository;
    }

    public Feedback addFeedback(Feedback feedback) { return feedbackRepo.save(feedback); }

    public List<Feedback> findAll(){ return feedbackRepo.findAll(); }

    //query method (auto generates method in spring back-backend)
    @Transactional
    public void deleteFeedback(Long id) { feedbackRepo.deleteFeedbackById(id);}

    public Feedback findFeedbackById(Long id) {
        return feedbackRepo.findFeedbackById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entity not foud with id: " + id)) ;
    }

    public List<Feedback> findAllOrderByDate() {
        return feedbackRepo.findAllByOrderByGeneratedDate();
    }

    public Feedback updateFeedback(Long id, Feedback feedback) {
        Feedback find = findFeedbackById(id);
        if (find != null){
            try{
                if (feedback.getQuestion() != null && find.getQuestion() != feedback.getQuestion()){
                    find.setQuestion(feedback.getQuestion());
                }
            }
            catch (Exception e){}

            try{
                if (feedback.getLifetime() != null && find.getLifetime() != feedback.getLifetime()){
                    find.setLifetime(feedback.getLifetime());
                }
            }
            catch (Exception e){}

            try{
                if (feedback.getFeedbackType() != null && find.getFeedbackType() != feedback.getFeedbackType()){
                    find.setFeedbackType(feedback.getFeedbackType());
                }
            }
            catch (Exception e){}
        }
        return null;
    }
}