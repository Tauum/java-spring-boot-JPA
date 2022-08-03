package com.example.javaspringboot.Repo.User;

import com.example.javaspringboot.Model.User.EnumRole;
import com.example.javaspringboot.Model.User.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(EnumRole name);

    Role findRoleById(Long roleId);
}