package com.example.javaspringboot.Repo;

import com.example.javaspringboot.Model.EnumRole;
import com.example.javaspringboot.Model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(EnumRole name);
}