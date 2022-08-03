package com.example.javaspringboot.Repo.User;

import com.example.javaspringboot.Model.User.Role;
import com.example.javaspringboot.Model.User.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    User findUserById(Long id);
    User findUserByEmail(String email);
    void deleteUserById(Long id);
    List<User> findAllByRolesContains(Role findRole);


}