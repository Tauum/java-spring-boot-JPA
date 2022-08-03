package com.example.javaspringboot.Repo.Additional;

import com.example.javaspringboot.Model.Additional.QuickNote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface QuickNoteRepo extends JpaRepository<QuickNote, Long> {

    void deleteQuickNoteById(Long id);

    Optional<QuickNote> findQuickNoteById(Long id);

    List<QuickNote> findAll();

    List<QuickNote> findAllByUserId(Long id);
}
