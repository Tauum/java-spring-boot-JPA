package com.example.javaspringboot.Controller.Games;

import com.example.javaspringboot.Model.Games.Propagate;
import com.example.javaspringboot.Service.Games.PropagateService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Propagate")
@CrossOrigin
public class PropagateController {
    private final PropagateService PropagateService;

    public PropagateController(PropagateService PropagateService) {
        this.PropagateService = PropagateService;
    }

    @GetMapping
    public ResponseEntity<List<Propagate>> getAllPropagates()
    {
        List<Propagate> Propagates = PropagateService.findAll();
        return new ResponseEntity<>(Propagates, HttpStatus.OK); //ok is 200 status code
    }

    @GetMapping("/{id}")
    public ResponseEntity<Propagate> getPropagate(@PathVariable("id") Long id)
    {
        Propagate Propagate = PropagateService.findPropagateById(id);
        return new ResponseEntity<>(Propagate, HttpStatus.OK); //ok is 200 status code
    }
    @GetMapping("/newestOrder")
    public ResponseEntity<List<Propagate>> getAllOrderedByDatePropagates()
    {
        List<Propagate> Propagates = PropagateService.findAllOrderByGeneratedDateDesc();
        return new ResponseEntity<>(Propagates, HttpStatus.OK); //ok is 200 status code
    }

    @GetMapping("/newestOrder-hideHidden")
    public ResponseEntity<List<Propagate>> getAllPropagateOrderedByDateAndHideHidden()
    {
        List<Propagate> Propagates = PropagateService.findAllOrderByGeneratedDateDescAndNotHidden();
        return new ResponseEntity<>(Propagates, HttpStatus.OK); //ok is 200 status code
    }

    @GetMapping("/latest")
    public ResponseEntity<Propagate> getLatestPropagateAndHideHidden()
    {
        Propagate Propagate = PropagateService.findPropagateOrderByGeneratedDateDescNotHidden();
        return new ResponseEntity<>(Propagate, HttpStatus.OK); //ok is 200 status code
    }

    @PostMapping("/getPropagateWithID")
    public ResponseEntity<Propagate> getPropagateWithID(@RequestBody Propagate Propagate)
    {
        Propagate attempt = PropagateService.findPropagateById(Propagate.getId());
        return new ResponseEntity<>(attempt, HttpStatus.OK); //ok is 200 status code
    }

    @PostMapping("/add")
    public ResponseEntity<Propagate> addPropagate(@RequestBody Propagate Propagate)
    {
        Propagate newPropagate = PropagateService.addPropagate(Propagate);
        return new ResponseEntity<>(Propagate, HttpStatus.CREATED); //ok is 200 status code
    }

//    // "A collection with cascade=\"all-delete-orphan\" was no longer referenced by the owning entity instance: uk.ac.bolton.backend.Model.Propagate.questions
//    @PutMapping("/update/{id}") // this doesnt work
//    public ResponseEntity<Propagate> updatePropagate(@PathVariable("id") Long id, @RequestBody Propagate Propagate)
//    {
//        Propagate attempt = PropagateService.findPropagateById(id);
//
//        if (attempt != null){
//            attempt.setTitle(Propagate.title);
//            attempt.setTimeLimit(Propagate.timeLimit);
//            attempt.setValue(Propagate.value);
//
//            attempt.questions.clear();
//
//            attempt.setQuestions(Propagate.questions);
//
//            Propagate updatedPropagate = PropagateService.updatePropagate(attempt);
//            // potentially do this? V delete questions and answers and rewrite them
//            // PropagateService.delete
//            return new ResponseEntity<>(updatedPropagate, HttpStatus.OK);  //ok is 200 status code
//        }
//        return new ResponseEntity<>(attempt, HttpStatus.BAD_REQUEST);
//
////        Propagate updatePropagate = PropagateService.updatePropagate(Propagate);
////        return new ResponseEntity<>(updatePropagate, HttpStatus.OK);  //ok is 200 status code
//    }

    // "A collection with cascade=\"all-delete-orphan\" was no longer referenced by the owning entity instance: uk.ac.bolton.backend.Model.Propagate.questions
//    @PutMapping("/update/{id}") // this doesnt work
//    public ResponseEntity<String> updatePropagate(@PathVariable("id") Long id, @RequestBody Propagate Propagate) {
//        Propagate attempt = PropagateService.updatePropagate(id, Propagate);
//        if (attempt != null) {
//            return new ResponseEntity<>("good", HttpStatus.OK);  //ok is 200 status code
//        }
//        return new ResponseEntity<>("bad", HttpStatus.BAD_REQUEST);
//    }



    @DeleteMapping("/delete/{id}") // THIS DOESNT DELETE QUESTIONS OR ANSWERS
    public ResponseEntity<?> deletePropagate(@PathVariable("id") Long id)
    {
        PropagateService.findPropagateById(id);
        PropagateService.deletePropagate(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}