package com.example.javaspringboot.Repository;

import com.example.javaspringboot.Model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findRoleByName(String name);
    void deleteRoleById(Long id);
    Role findRoleById(Long id);

}
