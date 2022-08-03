package com.example.javaspringboot.Controller.Submissions;


import com.example.javaspringboot.Model.Submissions.SubmittedQuiz;
import com.example.javaspringboot.Service.Submissions.SubmittedQuizService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/SubmittedQuiz")
@CrossOrigin
public class SubmittedQuizController {
    private final SubmittedQuizService submittedQuizService;

    public SubmittedQuizController(SubmittedQuizService submittedQuizService) {
        this.submittedQuizService = submittedQuizService;
    }

    @GetMapping
    public ResponseEntity<List<SubmittedQuiz>> getAllSubmittedQuizs()
    {
        List<SubmittedQuiz> quizSubmitteds = submittedQuizService.findAll();
        return new ResponseEntity<>(quizSubmitteds, HttpStatus.OK); //ok is 200 status code
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubmittedQuiz> getSubmittedQuiz(@PathVariable("id") Long id)
    {
        SubmittedQuiz quizSubmitted = submittedQuizService.findSubmittedQuizById(id);
        return new ResponseEntity<>(quizSubmitted, HttpStatus.OK); //ok is 200 status code
    }

    @GetMapping("/getForUser/{id}")
    public ResponseEntity<List<SubmittedQuiz>> getSubmittedQuizForUser(@PathVariable("id") Long id){
        List<SubmittedQuiz> quizzes = submittedQuizService.findAllByUserId(id);
        return new ResponseEntity<>(quizzes, HttpStatus.OK); //ok is 200 status code
    }

    @PostMapping("/add")
    public ResponseEntity<SubmittedQuiz> addSubmittedQuiz(@RequestBody SubmittedQuiz quizSubmitted)
    {
        SubmittedQuiz newSubmittedQuiz = submittedQuizService.addSubmittedQuiz(quizSubmitted);
        return new ResponseEntity<>(newSubmittedQuiz, HttpStatus.CREATED); //ok is 200 status code
    }

    @PutMapping("/update")
    public ResponseEntity<SubmittedQuiz> updateSubmittedQuiz(@RequestBody SubmittedQuiz quizSubmitted)
    {
        SubmittedQuiz updateSubmittedQuiz = submittedQuizService.updateSubmittedQuiz(quizSubmitted);
        return new ResponseEntity<>(updateSubmittedQuiz, HttpStatus.OK);  //ok is 200 status code
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteSubmittedQuiz(@PathVariable("id") Long id)
    {
        submittedQuizService.deleteSubmittedQuiz(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
