package com.example.javaspringboot.Submissions.Controller;


import com.example.javaspringboot.Submissions.Model.SubmittedSwipe;
import com.example.javaspringboot.Submissions.Service.SubmittedSwipeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/SubmittedSwipes")
public class SubmittedSwipeController {
    private final SubmittedSwipeService submittedSwipeService;

    public SubmittedSwipeController(SubmittedSwipeService submittedSwipeService) {
        this.submittedSwipeService = submittedSwipeService;
    }

    @GetMapping
    public ResponseEntity<List<SubmittedSwipe>> getAllSubmittedSwipes()
    {
        List<SubmittedSwipe> SwipeSubmitteds = submittedSwipeService.findAll();
        return new ResponseEntity<>(SwipeSubmitteds, HttpStatus.OK); //ok is 200 status code
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubmittedSwipe> getSubmittedSwipe(@PathVariable("id") Long id)
    {
        SubmittedSwipe SwipeSubmitted = submittedSwipeService.findSubmittedSwipeById(id);
        return new ResponseEntity<>(SwipeSubmitted, HttpStatus.OK); //ok is 200 status code
    }

    @GetMapping("/getForUser/{id}")
    public ResponseEntity<List<SubmittedSwipe>> getSubmittedSwipeForUser(@PathVariable("id") Long id){
        List<SubmittedSwipe> Swipezes = submittedSwipeService.findAllByUserId(id);
        return new ResponseEntity<>(Swipezes, HttpStatus.OK); //ok is 200 status code
    }

    @PostMapping("/add")
    public ResponseEntity<SubmittedSwipe> addSubmittedSwipe(@RequestBody SubmittedSwipe SwipeSubmitted)
    {
        SubmittedSwipe newSubmittedSwipe = submittedSwipeService.addSubmittedSwipe(SwipeSubmitted);
        return new ResponseEntity<>(newSubmittedSwipe, HttpStatus.CREATED); //ok is 200 status code
    }

    @PutMapping("/update")
    public ResponseEntity<SubmittedSwipe> updateSubmittedSwipe(@RequestBody SubmittedSwipe SwipeSubmitted)
    {
        SubmittedSwipe updateSubmittedSwipe = submittedSwipeService.updateSubmittedSwipe(SwipeSubmitted);
        return new ResponseEntity<>(updateSubmittedSwipe, HttpStatus.OK);  //ok is 200 status code
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteSubmittedSwipe(@PathVariable("id") Long id)
    {
        submittedSwipeService.deleteSubmittedSwipe(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
