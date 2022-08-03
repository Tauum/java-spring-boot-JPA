package com.example.javaspringboot.Service.Additional;


import com.example.javaspringboot.Model.Additional.ContactForm;
import com.example.javaspringboot.Repo.Additional.ContactFormRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class ContactFormService {

    private final com.example.javaspringboot.Repo.Additional.ContactFormRepo ContactFormRepo;

    @Autowired
    public ContactFormService(ContactFormRepo ContactFormRepository) {
        this.ContactFormRepo = ContactFormRepository;
    }

    public ContactForm addContactForm(ContactForm ContactForm) { return ContactFormRepo.save(ContactForm); }

    public List<ContactForm> findAll(){ return ContactFormRepo.findAll(); }

    //query method (auto generates method in spring back-backend)
    @Transactional
    public void deleteContactForm(Long id) { ContactFormRepo.deleteContactFormById(id);}

    public ContactForm findContactFormById(Long id) {
        return ContactFormRepo.findContactFormById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entity not foud with id: " + id)) ;
    }

    public List<ContactForm> findAllOrderByDate() {
        return ContactFormRepo.findAllByOrderByGeneratedDate();
    }

}