package com.example.javaspringboot.Service.Additional;

import com.example.javaspringboot.Model.Additional.QuickNote;
import com.example.javaspringboot.Repo.Additional.QuickNoteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class QuickNoteService {
    private final com.example.javaspringboot.Repo.Additional.QuickNoteRepo QuickNoteRepo;

    @Autowired
    public QuickNoteService(QuickNoteRepo QuickNoteRepository) {
        this.QuickNoteRepo = QuickNoteRepository;
    }

    public QuickNote addQuickNote(QuickNote QuickNote) { return QuickNoteRepo.save(QuickNote); }

    //query method (auto generates method in spring back-backend)
    @Transactional
    public void deleteQuickNote(Long id) { QuickNoteRepo.deleteQuickNoteById(id);}

    public QuickNote findQuickNoteById(Long id)
    {
        return QuickNoteRepo.findQuickNoteById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entity not foud with id: " + id)) ;
    }


    public List<QuickNote> findAll() {
        return QuickNoteRepo.findAll();
    }

    public List<QuickNote> findAllByUserId(Long id) {
        return QuickNoteRepo.findAllByUserId(id);
    }
}