package com.example.javaspringboot.Additional.Controller;


import com.example.javaspringboot.Additional.Model.Extra;
import com.example.javaspringboot.Additional.Service.ExtraService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Extras")
public class ExtraController {
    private final com.example.javaspringboot.Additional.Service.ExtraService ExtraService;

    public ExtraController(ExtraService ExtraService) {
        this.ExtraService = ExtraService;
    }

    @GetMapping
    public ResponseEntity<List<Extra>> getAllExtras()
    {
        List<Extra> Extras = ExtraService.findAll();
        return new ResponseEntity<>(Extras, HttpStatus.OK); //ok is 200 status code
    }

    @GetMapping("/allOrderByDate")
    public ResponseEntity<List<Extra>> getAllExtrasOrderedByDate()
    {
        List<Extra> Extras = ExtraService.findAllOrderByDate();
        return new ResponseEntity<>(Extras, HttpStatus.OK); //ok is 200 status code
    }

    @GetMapping("/newestOrder-hideHidden")
    public ResponseEntity<List<Extra>> getAllQuizOrderedByDateAndHideHidden()
    {
        List<Extra> Extras = ExtraService.findAllOrderByGeneratedDateDescAndNotHidden();
        return new ResponseEntity<>(Extras, HttpStatus.OK); //ok is 200 status code
    }


    @GetMapping("/{id}")
    public ResponseEntity<Extra> getExtra(@PathVariable("id") Long id)
    {
        Extra Extra = ExtraService.findExtraById(id);
        return new ResponseEntity<>(Extra, HttpStatus.OK); //ok is 200 status code
    }

    @PostMapping("/getExtrasContainingTitle")
    public ResponseEntity<List<Extra>> getExtrasContainingTitle(@RequestBody Extra Extra)
    {
        List<Extra> Extras = ExtraService.findAllContainingTitle(Extra.getTitle());
        return new ResponseEntity<>(Extras, HttpStatus.OK); //ok is 200 status code
    }

    @PostMapping("/add")
    public ResponseEntity<Extra> addExtra(@RequestBody Extra Extra)
    {
        Extra newExtra = ExtraService.addExtra(Extra);
        return new ResponseEntity<>(Extra, HttpStatus.CREATED); //ok is 200 status code
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Extra> updateHangman(@PathVariable("id") Long id, @RequestBody Extra Extra)
    {
        Extra attempt = ExtraService.findExtraById(id);
        if (attempt != null){
            attempt.setTitle(Extra.title);
            attempt.setSummary(Extra.summary);
            attempt.setAuthor(Extra.author);
            attempt.setVideo(Extra.video);
            attempt.setContent(Extra.content);
            attempt.setHidden(Extra.hidden);
            Extra updatedExtra = ExtraService.updateExtra(attempt);
            return new ResponseEntity<>(updatedExtra, HttpStatus.OK);  //ok is 200 status code
        }
        return new ResponseEntity<>(attempt, HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteExtra(@PathVariable("id") Long id)
    {
        ExtraService.deleteExtra(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
