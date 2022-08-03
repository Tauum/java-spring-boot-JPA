package com.example.javaspringboot.Controller.Submissions;


import com.example.javaspringboot.Model.Submissions.SubmittedHangman;
import com.example.javaspringboot.Service.Submissions.SubmittedHangmanService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/SubmittedHangman")
@CrossOrigin
public class SubmittedHangmanController {
    private final SubmittedHangmanService submittedHangmanService;

    public SubmittedHangmanController(SubmittedHangmanService submittedHangmanService) {
        this.submittedHangmanService = submittedHangmanService;
    }

    @GetMapping
    public ResponseEntity<List<SubmittedHangman>> getAllSubmittedHangmans()
    {
        List<SubmittedHangman> submittedHangmans = submittedHangmanService.findAll();
        return new ResponseEntity<>(submittedHangmans, HttpStatus.OK); //ok is 200 status code
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubmittedHangman> getSubmittedHangman(@PathVariable("id") Long id)
    {
        SubmittedHangman submittedHangman = submittedHangmanService.findSubmittedHangmanById(id);
        return new ResponseEntity<>(submittedHangman, HttpStatus.OK); //ok is 200 status code
    }

    @GetMapping("/getForUser/{id}")
    public ResponseEntity<List<SubmittedHangman>> getSubmittedQuizForUser(@PathVariable("id") Long id){
        List<SubmittedHangman> submittedHangmans = submittedHangmanService.findAllByUserId(id);
        return new ResponseEntity<>(submittedHangmans, HttpStatus.OK); //ok is 200 status code
    }


    @PostMapping("/add")
    public ResponseEntity<SubmittedHangman> addSubmittedHangman(@RequestBody SubmittedHangman submittedHangman)
    {
        SubmittedHangman newSubmittedHangman = submittedHangmanService.addSubmittedHangman(submittedHangman);
        return new ResponseEntity<>(submittedHangman, HttpStatus.CREATED); //ok is 200 status code
    }

    @PutMapping("/update")
    public ResponseEntity<SubmittedHangman> updateSubmittedHangman(@RequestBody SubmittedHangman submittedHangman)
    {
        SubmittedHangman updateSubmittedHangman = submittedHangmanService.updateSubmittedHangman(submittedHangman);
        return new ResponseEntity<>(updateSubmittedHangman, HttpStatus.OK);  //ok is 200 status code
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteSubmittedHangman(@PathVariable("id") Long id)
    {
        submittedHangmanService.deleteSubmittedHangman(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

