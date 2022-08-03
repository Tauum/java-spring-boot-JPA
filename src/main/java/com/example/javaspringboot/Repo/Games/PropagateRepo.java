package com.example.javaspringboot.Repo.Games;

import com.example.javaspringboot.Model.Games.Propagate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PropagateRepo extends JpaRepository<Propagate, Long> {

    void deletePropagateById(Long id);

    List<Propagate> findAllByOrderByGeneratedDateDesc();

    Propagate findFirstByOrderByIdDesc();

    List<Propagate> findAllByHiddenOrderByGeneratedDateDesc(boolean hidden);

    Propagate findFirstByHiddenOrderByIdDesc(boolean hidden);

    Optional<Object> findPropagateById(Long id);
}
