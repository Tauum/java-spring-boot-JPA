package com.example.javaspringboot.Repository;

import com.example.javaspringboot.Model.Role;
import com.example.javaspringboot.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface UserRepository extends JpaRepository<User, Long> {

    User findUserById(Long id);
    User findUserByEmail(String email);
    void deleteUserById(Long id);
    List<User> findAllByRolesContains(Role findRole);


}
