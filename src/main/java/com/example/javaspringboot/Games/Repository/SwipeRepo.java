package com.example.javaspringboot.Games.Repository;

import com.example.javaspringboot.Games.Model.Swipe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SwipeRepo extends JpaRepository<Swipe, Long> {

    void deleteSwipeById(Long id);

    List<Swipe> findAllByOrderByGeneratedDateDesc();

    Swipe findFirstByOrderByIdDesc();

    List<Swipe> findAllByHiddenOrderByGeneratedDateDesc(boolean hidden);

    Swipe findFirstByHiddenOrderByIdDesc(boolean hidden);

    Swipe findSwipeById(Long id);
}
