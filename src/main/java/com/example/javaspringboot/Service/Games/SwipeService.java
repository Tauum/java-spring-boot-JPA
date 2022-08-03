package com.example.javaspringboot.Service.Games;


import com.example.javaspringboot.Model.Games.Swipe;
import com.example.javaspringboot.Model.Games.Swipe_Card;
import com.example.javaspringboot.Repo.Games.SwipeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class SwipeService {
    private final com.example.javaspringboot.Repo.Games.SwipeRepo SwipeRepo;

    @Autowired
    public SwipeService(SwipeRepo SwipeRepository) {
        this.SwipeRepo = SwipeRepository;
    }

    public Swipe addSwipe(Swipe Swipe)
    {
        return SwipeRepo.save(Swipe);
    }

    public List<Swipe> findAll(){ return SwipeRepo.findAll(); }

    public Swipe updateSwipe(Swipe Swipe){ return SwipeRepo.save(Swipe); }

    //query method (auto generates method in spring back-backend)
    @Transactional
    public void deleteSwipe(Long id) { SwipeRepo.deleteSwipeById(id);}

    public Swipe updateSwipe(Long id, Swipe attempt) {
        Swipe find = findSwipeById(id);
        if (find != null) {
            find.setGeneral(attempt.getTitle(),attempt.getValue(),attempt.isHidden(),attempt.getSubject());

            ArrayList<Swipe_Card> remC = new ArrayList<>();
            ArrayList<Swipe_Card> addC = new ArrayList<>();

            // this is for updating questions
            for(Swipe_Card attemptCurC: attempt.cards){
                for (Swipe_Card findCurC : find.cards){
                    if (attemptCurC.getId()!= null ) {
                        if (attemptCurC.getId().equals(findCurC.getId())) {
                            findCurC.setGeneral(attemptCurC.getValue(), attemptCurC.getQuestion(), attemptCurC.getSubText(),attemptCurC.getImageURL(),attemptCurC.getCorrect() );
                        }
                    }
                    else{ if (!addC.contains(attemptCurC)) { addC.add(attemptCurC); } } // double condition to prevent being added twice
                }
            }
            // this is for removing a question that no longer exists
            for(Swipe_Card findCurC: find.cards ){
                boolean qExists = false;
                for (Swipe_Card attemptCurC : attempt.cards){
                    if (attemptCurC.getId() == null) {  qExists = true; }
                    else{  if (findCurC.getId().equals(attemptCurC.getId())) { qExists = true; }  } // double condition to prevent being added twice
                }
                if (!qExists && !remC.contains(findCurC)) { remC.add(findCurC); } // double condition to prevent being added twice
            }
            find.cards.addAll(addC);
            find.cards.removeAll(remC);
            SwipeRepo.save(find);
        }
        return find;
    }



    public Swipe findSwipeById(Long id)
    {
        return (Swipe) SwipeRepo.findSwipeById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entity not foud with id: " + id));
    }

    public List<Swipe> findAllOrderByGeneratedDateDesc() {
        return SwipeRepo.findAllByOrderByGeneratedDateDesc();
    }

    public List<Swipe> findAllOrderByGeneratedDateDescAndNotHidden() {
        return SwipeRepo.findAllByHiddenOrderByGeneratedDateDesc(false);
    }

    public Swipe findSwipeOrderByGeneratedDateDescNotHidden() {
        return SwipeRepo.findFirstByHiddenOrderByIdDesc(false);
    }


}

