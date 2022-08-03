package com.example.javaspringboot.Controller.Additional;


import com.example.javaspringboot.Model.Additional.ContactForm;
import com.example.javaspringboot.Service.Additional.ContactFormService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ContactForm")
@CrossOrigin
public class ContactFormController {

    private final ContactFormService ContactFormService;

    public ContactFormController(ContactFormService ContactFormService) {
        this.ContactFormService = ContactFormService;
    }

    @GetMapping
    public ResponseEntity<List<ContactForm>> getAllContactForm()
    {
        List<ContactForm> ContactForms = ContactFormService.findAllOrderByDate();
        return new ResponseEntity<>(ContactForms, HttpStatus.OK); //ok is 200 status code
    }

    @PostMapping("/add")
    public ResponseEntity<ContactForm> addContactForm(@RequestBody ContactForm ContactForm)
    {
        ContactForm newContactForm = ContactFormService.addContactForm(ContactForm);
        return new ResponseEntity<>(ContactForm, HttpStatus.CREATED); //ok is 200 status code
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteContactForm(@PathVariable("id") Long id)
    {
        ContactForm attempt = ContactFormService.findContactFormById(id);
        ContactFormService.deleteContactForm(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete/all")
    public ResponseEntity<?> deleteContactForm()
    {
        List<ContactForm> attempt = ContactFormService.findAll();
        for (int i = 0; i < attempt.size(); i++) {

            Long a = Long.valueOf(i);
            ContactForm current= attempt.get(i);
            ContactFormService.deleteContactForm(current.getId());
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

