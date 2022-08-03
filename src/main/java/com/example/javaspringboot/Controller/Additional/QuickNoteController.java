package com.example.javaspringboot.Controller.Additional;

import com.example.javaspringboot.Model.Additional.QuickNote;
import com.example.javaspringboot.Service.Additional.QuickNoteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/QuickNote")
@CrossOrigin
public class QuickNoteController {
    private final com.example.javaspringboot.Service.Additional.QuickNoteService QuickNoteService;

    public QuickNoteController(QuickNoteService QuickNoteService) {this.QuickNoteService = QuickNoteService; }

    @GetMapping
    public ResponseEntity<List<QuickNote>> getAllQuickNotes()
    {
        List<QuickNote> QuickNotes = QuickNoteService.findAll();
        return new ResponseEntity<>(QuickNotes, HttpStatus.OK); //ok is 200 status code
    }
    
    @GetMapping("/getAllForUser/{id}")
    public ResponseEntity<List<QuickNote>> GetAllForUser(@PathVariable("id") Long id){
        List<QuickNote> QuickNotes = QuickNoteService.findAllByUserId(id);
        return new ResponseEntity<>(QuickNotes, HttpStatus.OK); //ok is 200 status code
    }

    @PostMapping("/add")
    public ResponseEntity<QuickNote> addQuickNote(@RequestBody QuickNote QuickNote)
    {
        QuickNote newQuickNote = QuickNoteService.addQuickNote(QuickNote);
        return new ResponseEntity<>(QuickNote, HttpStatus.CREATED); //ok is 200 status code
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteQuickNote(@PathVariable("id") Long id)
    {
        QuickNote attempt = QuickNoteService.findQuickNoteById(id);
        QuickNoteService.deleteQuickNote(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

