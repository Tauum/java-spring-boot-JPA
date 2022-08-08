package com.example.javaspringboot.Games.Repository;

import com.example.javaspringboot.Games.Model.Propagate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PropagateRepo extends JpaRepository<Propagate, Long> {

    void deletePropagateById(Long id);

    List<Propagate> findAllByOrderByGeneratedDateDesc();

    Propagate findFirstByOrderByIdDesc();

    List<Propagate> findAllByHiddenOrderByGeneratedDateDesc(boolean hidden);

    Propagate findFirstByHiddenOrderByIdDesc(boolean hidden);

    Propagate findPropagateById(Long id);
}
