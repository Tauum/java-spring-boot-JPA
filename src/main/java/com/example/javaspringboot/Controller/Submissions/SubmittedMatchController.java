package com.example.javaspringboot.Controller.Submissions;


import com.example.javaspringboot.Model.Submissions.SubmittedMatch;
import com.example.javaspringboot.Service.Submissions.SubmittedMatchService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/SubmittedMatch")
@CrossOrigin
public class SubmittedMatchController {
    private final SubmittedMatchService submittedMatchService;

    public SubmittedMatchController(SubmittedMatchService submittedMatchService) {
        this.submittedMatchService = submittedMatchService;
    }

    @GetMapping
    public ResponseEntity<List<SubmittedMatch>> getAllSubmittedMatches()
    {
        List<SubmittedMatch> matchSubmitteds = submittedMatchService.findAll();
        return new ResponseEntity<>(matchSubmitteds, HttpStatus.OK); //ok is 200 status code
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubmittedMatch> getSubmittedMatch(@PathVariable("id") Long id)
    {
        SubmittedMatch matchSubmitted = submittedMatchService.findSubmittedMatchById(id);
        return new ResponseEntity<>(matchSubmitted, HttpStatus.OK); //ok is 200 status code
    }

    @GetMapping("/getForUser/{id}")
    public ResponseEntity<List<SubmittedMatch>> getSubmittedMatchForUser(@PathVariable("id") Long id){
        List<SubmittedMatch> matchzes = submittedMatchService.findAllByUserId(id);
        return new ResponseEntity<>(matchzes, HttpStatus.OK); //ok is 200 status code
    }

    @PostMapping("/add")
    public ResponseEntity<SubmittedMatch> addSubmittedMatch(@RequestBody SubmittedMatch matchSubmitted)
    {
        SubmittedMatch newSubmittedMatch = submittedMatchService.addSubmittedMatch(matchSubmitted);
        return new ResponseEntity<>(newSubmittedMatch, HttpStatus.CREATED); //ok is 200 status code
    }

    @PutMapping("/update")
    public ResponseEntity<SubmittedMatch> updateSubmittedMatch(@RequestBody SubmittedMatch matchSubmitted)
    {
        // redo save function here
        SubmittedMatch updateSubmittedMatch = submittedMatchService.updateSubmittedMatch(matchSubmitted);
        return new ResponseEntity<>(updateSubmittedMatch, HttpStatus.OK);  //ok is 200 status code
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteSubmittedMatch(@PathVariable("id") Long id)
    {
        submittedMatchService.deleteSubmittedMatch(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
