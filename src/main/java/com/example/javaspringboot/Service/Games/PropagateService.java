package com.example.javaspringboot.Service.Games;


import com.example.javaspringboot.Model.Games.Propagate;
import com.example.javaspringboot.Repo.Games.PropagateRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class PropagateService {
    private final com.example.javaspringboot.Repo.Games.PropagateRepo PropagateRepo;

    @Autowired
    public PropagateService(PropagateRepo PropagateRepository) {
        this.PropagateRepo = PropagateRepository;
    }

    public Propagate addPropagate(Propagate Propagate)
    {
        return PropagateRepo.save(Propagate);
    }

    public List<Propagate> findAll(){ return PropagateRepo.findAll(); }

    public Propagate updatePropagate(Propagate Propagate){ return PropagateRepo.save(Propagate); }

    //query method (auto generates method in spring back-backend)
    @Transactional
    public void deletePropagate(Long id) { PropagateRepo.deletePropagateById(id);}

//    public Propagate updatePropagate(Long id, Propagate attempt) {
//        Propagate find = findPropagateById(id);
//        if (find != null) {
//            find.setGeneral(attempt.getTitle(),attempt.getValue(),attempt.isHidden(),attempt.getSubject());
//
//            ArrayList<Card> remC = new ArrayList<>();
//            ArrayList<Card> addC = new ArrayList<>();
//
//            // this is for updating questions
//            for(Card attemptCurC: attempt.cards){
//                for (Card findCurC : find.cards){
//                    if (attemptCurC.getId()!= null ) {
//                        if (attemptCurC.getId().equals(findCurC.getId())) {
//                            findCurC.setGeneral(attemptCurC.getValue(), attemptCurC.getQuestion(), attemptCurC.getSubText(),attemptCurC.getImageURL(),attemptCurC.getCorrect() );
//                        }
//                    }
//                    else{ if (!addC.contains(attemptCurC)) { addC.add(attemptCurC); } } // double condition to prevent being added twice
//                }
//            }
//            // this is for removing a question that no longer exists
//            for(Card findCurC: find.cards ){
//                boolean qExists = false;
//                for (Card attemptCurC : attempt.cards){
//                    if (attemptCurC.getId() == null) {  qExists = true; }
//                    else{  if (findCurC.getId().equals(attemptCurC.getId())) { qExists = true; }  } // double condition to prevent being added twice
//                }
//                if (!qExists && !remC.contains(findCurC)) { remC.add(findCurC); } // double condition to prevent being added twice
//            }
//            find.cards.addAll(addC);
//            find.cards.removeAll(remC);
//            PropagateRepo.save(find);
//        }
//        return find;
//    }



    public Propagate findPropagateById(Long id)
    {
        return (Propagate) PropagateRepo.findPropagateById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entity not foud with id: " + id));
    }

    public List<Propagate> findAllOrderByGeneratedDateDesc() {
        return PropagateRepo.findAllByOrderByGeneratedDateDesc();
    }

    public List<Propagate> findAllOrderByGeneratedDateDescAndNotHidden() {
        return PropagateRepo.findAllByHiddenOrderByGeneratedDateDesc(false);
    }

    public Propagate findPropagateOrderByGeneratedDateDescNotHidden() {
        return PropagateRepo.findFirstByHiddenOrderByIdDesc(false);
    }


}

