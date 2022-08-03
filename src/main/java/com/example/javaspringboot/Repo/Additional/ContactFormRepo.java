package com.example.javaspringboot.Repo.Additional;

import com.example.javaspringboot.Model.Additional.ContactForm;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ContactFormRepo extends JpaRepository<ContactForm, Long> {

    Optional<ContactForm> findContactFormById(Long id);

    void deleteContactFormById(Long id);

    List<ContactForm> findAllByOrderByGeneratedDate();
}

