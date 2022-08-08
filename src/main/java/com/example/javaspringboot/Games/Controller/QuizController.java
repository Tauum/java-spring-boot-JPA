package com.example.javaspringboot.Games.Controller;

import com.example.javaspringboot.Games.Model.Quiz;
import com.example.javaspringboot.Games.Service.QuizService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Quizzes")
public class QuizController {
    private final QuizService QuizService;

    public QuizController(QuizService QuizService) {
        this.QuizService = QuizService;
    }

    @GetMapping
    public ResponseEntity<List<Quiz>> getAllQuizs()
    {
        List<Quiz> Quizs = QuizService.findAll();
        return new ResponseEntity<>(Quizs, HttpStatus.OK); //ok is 200 status code
    }

    @GetMapping("/{id}")
    public ResponseEntity<Quiz> getQuiz(@PathVariable("id") Long id)
    {
        Quiz Quiz = QuizService.findQuizById(id);
        return new ResponseEntity<>(Quiz, HttpStatus.OK); //ok is 200 status code
    }
    @GetMapping("/newestOrder")
    public ResponseEntity<List<Quiz>> getAllOrderedByDateQuizs()
    {
        List<Quiz> Quizs = QuizService.findAllOrderByGeneratedDateDesc();
        return new ResponseEntity<>(Quizs, HttpStatus.OK); //ok is 200 status code
    }

    @GetMapping("/newestOrder-hideHidden")
    public ResponseEntity<List<Quiz>> getAllQuizOrderedByDateAndHideHidden()
    {
        List<Quiz> Quizs = QuizService.findAllOrderByGeneratedDateDescAndNotHidden();
        return new ResponseEntity<>(Quizs, HttpStatus.OK); //ok is 200 status code
    }

    @GetMapping("/latest")
    public ResponseEntity<Quiz> getLatestQuizAndHideHidden()
    {
        Quiz Quiz = QuizService.findQuizOrderByGeneratedDateDescNotHidden();
        return new ResponseEntity<>(Quiz, HttpStatus.OK); //ok is 200 status code
    }

    @PostMapping("/getQuizWithID")
    public ResponseEntity<Quiz> getQuizWithID(@RequestBody Quiz Quiz)
    {
        Quiz attempt = QuizService.findQuizById(Quiz.getId());
        return new ResponseEntity<>(attempt, HttpStatus.OK); //ok is 200 status code
    }

    @PostMapping("/add")
    public ResponseEntity<Quiz> addQuiz(@RequestBody Quiz Quiz)
    {
        Quiz newQuiz = QuizService.addQuiz(Quiz);
        return new ResponseEntity<>(Quiz, HttpStatus.CREATED); //ok is 200 status code
    }

//    // "A collection with cascade=\"all-delete-orphan\" was no longer referenced by the owning entity instance: uk.ac.bolton.backend.Model.Quiz.questions
//    @PutMapping("/update/{id}") // this doesnt work
//    public ResponseEntity<Quiz> updateQuiz(@PathVariable("id") Long id, @RequestBody Quiz Quiz)
//    {
//        Quiz attempt = QuizService.findQuizById(id);
//
//        if (attempt != null){
//            attempt.setTitle(Quiz.title);
//            attempt.setTimeLimit(Quiz.timeLimit);
//            attempt.setValue(Quiz.value);
//
//            attempt.questions.clear();
//
//            attempt.setQuestions(Quiz.questions);
//
//            Quiz updatedQuiz = QuizService.updateQuiz(attempt);
//            // potentially do this? V delete questions and answers and rewrite them
//            // QuizService.delete
//            return new ResponseEntity<>(updatedQuiz, HttpStatus.OK);  //ok is 200 status code
//        }
//        return new ResponseEntity<>(attempt, HttpStatus.BAD_REQUEST);
//
////        Quiz updateQuiz = QuizService.updateQuiz(Quiz);
////        return new ResponseEntity<>(updateQuiz, HttpStatus.OK);  //ok is 200 status code
//    }

    // "A collection with cascade=\"all-delete-orphan\" was no longer referenced by the owning entity instance: uk.ac.bolton.backend.Model.Quiz.questions
    @PutMapping("/update/{id}") // this doesnt work
    public ResponseEntity<String> updateQuiz(@PathVariable("id") Long id, @RequestBody Quiz Quiz) {
        Quiz attempt = QuizService.updateQuiz(id, Quiz);
        if (attempt != null) {
            return new ResponseEntity<>("good", HttpStatus.OK);  //ok is 200 status code
        }
        return new ResponseEntity<>("bad", HttpStatus.BAD_REQUEST);
    }



    @DeleteMapping("/delete/{id}") // THIS DOESNT DELETE QUESTIONS OR ANSWERS
    public ResponseEntity<?> deleteQuiz(@PathVariable("id") Long id)
    {
        QuizService.findQuizById(id);
        QuizService.deleteQuiz(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}