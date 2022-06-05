package com.example.javaspringboot.Controller;

import com.example.javaspringboot.Model.Role;
import com.example.javaspringboot.Service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController @RequiredArgsConstructor
@RequestMapping("/Role")
public class RoleController {
    private final RoleService RoleService;

    //@CrossOrigin( origins = "http://localhost:3000")
    @GetMapping("/{id}")
    public ResponseEntity<Role> getRoleByID(@PathVariable("id") Long id) {
        Role Role = RoleService.findRoleById(id);
        return new ResponseEntity<>(Role, HttpStatus.OK); //ok is 200 status code
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Role>> getAllRoles() {
        List<Role> Roles = RoleService.findAll();
        return new ResponseEntity<>(Roles, HttpStatus.OK); //ok is 200 status code
    }

    @GetMapping("/getByName")
    public ResponseEntity<Role> getRoleByName(@RequestBody String name) {
        Role attempt = RoleService.findRoleByName(name);
        return new ResponseEntity<>(attempt, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Role> addRole(@RequestBody Role Role) {
        Role newRole = RoleService.addRole(Role);
        return new ResponseEntity<>(Role, HttpStatus.CREATED); //ok is 201 status code
    }

    @PutMapping("/update")
    public ResponseEntity<Role> updateRole(@RequestBody Role Role) {
        Role updateRole = RoleService.updateRole(Role);
        return new ResponseEntity<>(updateRole, HttpStatus.OK);  //ok is 200 status code
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteRole(@PathVariable("id") Long id) {
        RoleService.deleteRole(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}