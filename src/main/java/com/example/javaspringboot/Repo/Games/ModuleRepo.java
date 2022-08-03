package com.example.javaspringboot.Repo.Games;

import com.example.javaspringboot.Model.Additional.Module;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ModuleRepo extends JpaRepository<Module, Long> {

    void deleteModuleById(Long id);

    Optional<Module> findModuleById(Long id);

    List<Module> findAll();

    List<Module> findByCodeContains(String code);

    Module findFirstByCodeContains(String code);

//    @EntityGraph(attributePaths = {"user"})
    List<Module> findByStudentsId(long id);
}
