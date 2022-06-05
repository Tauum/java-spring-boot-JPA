package com.example.javaspringboot;

import com.example.javaspringboot.Model.Role;
import com.example.javaspringboot.Model.User;
import com.example.javaspringboot.Service.RoleService;
import com.example.javaspringboot.Service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class JavaSpringBootApplication {
    // this runs on init
    public static void main(String[] args) {
        SpringApplication.run(JavaSpringBootApplication.class, args);
    }

    @Bean
    PasswordEncoder passwordEncoder(){ // NEEDED TO ALLOW PASSWORD ENCODER INSIDE SECURITY
        return new BCryptPasswordEncoder();
    }

    @Bean
    CommandLineRunner run(UserService userService, RoleService roleService){ // MOCK DATA
        return args -> {
//            roleService.addRole(new Role("UNDEFINED"));
//            Set<Role> userRoles = new HashSet<>();
//            userService.addUser(new User("test@test.com", "123", "test", "test", userRoles));

//            Set<Role> roles = roleService.findAll();
//            Set<User> users = userService.findAll();

//            if (roles.size() < 4){ // BELOW ONLY RUNS IF MISSING ROLES
//                if (roleService.findRoleByName("UNDEFINED") == null){ roleService.addRole(new Role("UNDEFINED")); }
//                if (roleService.findRoleByName("ADMIN") == null){ roleService.addRole(new Role("ADMIN")) ; }
//                if (roleService.findRoleByName("STAFF") == null){ roleService.addRole(new Role("STAFF")); }
//                if (roleService.findRoleByName("STUDENT") == null){ roleService.addRole(new Role("STUDENT")); }
//                if (roleService.findRoleByName("OTHER") == null){ roleService.addRole(new Role("OTHER")); }
//            }
//            if (users.size() > 1){
//                if (userService.findUserByEmail("test@test.com") == null){
//                    Role undefinedRole = roleService.findRoleByName("UNDEFINED");
//                    Set<Role> userRoles = new HashSet<>();
//                    userRoles.add(undefinedRole);
//                    userService.addUser(new User("test@test.com", "123", "test", "test", userRoles));
//                }
//            }
        };
    }
}
