package com.example.javaspringboot.Additional.Controller;

import com.example.javaspringboot.Additional.Model.Update;
import com.example.javaspringboot.Additional.Service.UpdateService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/Updates")
public class UpdateController {
    private final com.example.javaspringboot.Additional.Service.UpdateService UpdateService;

    public UpdateController(UpdateService UpdateService) {
        this.UpdateService = UpdateService;
    }

    @GetMapping
    public ResponseEntity<List<Update>> getAllUpdate()
    {
        List<Update> Updates = UpdateService.findAllOrderByDate();
        return new ResponseEntity<>(Updates, HttpStatus.OK); //ok is 200 status code
    }

    @GetMapping("/Recent")
    public ResponseEntity<List<Update>> getAllRecentUpdate()
    {
        LocalDate now = LocalDate.now();
        now = now.minusWeeks(2);
        List<Update> Updates = UpdateService.findAllRecentOrderByDate(now);
        return new ResponseEntity<>(Updates, HttpStatus.OK); //ok is 200 status code
    }

    @PostMapping("/add")
    public ResponseEntity<Update> addUpdate(@RequestBody Update Update)
    {
        Update newUpdate = UpdateService.addUpdate(Update);
        return new ResponseEntity<>(Update, HttpStatus.CREATED); //ok is 200 status code
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Update> updateUpdate(@PathVariable("id") Long id, @RequestBody Update Update)
    {
        Update attempt = UpdateService.findUpdateById(id);
        if (attempt != null){
            attempt.setGeneratedDate(Update.generatedDate);
            attempt.setContent(Update.content);
            Update updatedUpdate = UpdateService.updateUpdate(attempt);
            return new ResponseEntity<>(updatedUpdate, HttpStatus.OK);  //ok is 200 status code
        }
        return new ResponseEntity<>(attempt, HttpStatus.BAD_REQUEST);  //ok is 200 status code
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUpdate(@PathVariable("id") Long id)
    {
        Update attempt = UpdateService.findUpdateById(id);
        UpdateService.deleteUpdate(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

