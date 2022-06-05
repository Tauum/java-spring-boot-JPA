package com.example.javaspringboot.Controller;

import com.example.javaspringboot.Model.User;
import com.example.javaspringboot.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RestController @RequiredArgsConstructor
@RequestMapping("/User")
public class UserController {
    private final UserService userService;

    //@CrossOrigin( origins = "http://localhost:3000")
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserByID(@PathVariable("id") Long id) {
        User User = userService.findUserById(id);
        return new ResponseEntity<>(User, HttpStatus.OK); //ok is 200 status code
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK); //ok is 200 status code
    }

    @GetMapping("/getAllInRole")
    public ResponseEntity<List<User>> getAllUsersInRole(@RequestBody Long roleId) {
        List<User> users = userService.findByRoleContains(roleId);
        return new ResponseEntity<>(users, HttpStatus.OK); //ok is 200 status code
    }

    @GetMapping("/getByEmail")
    public ResponseEntity<User> getUserByEmail(@RequestBody String email) {
        User attempt = userService.findUserByEmail(email);
        return new ResponseEntity<>(attempt, HttpStatus.OK);
    }

    @PostMapping("/addRole")
    public ResponseEntity<Boolean> addRoleToUserByIds(@RequestBody Long userId, Long roleId) {
        Boolean attempt = userService.addRoleToUser(userId, roleId);
        return new ResponseEntity<>(attempt, HttpStatus.OK);
    }

    @PostMapping("/removeRole")
    public ResponseEntity<Boolean> removeRoleToUserByIds(@RequestBody Long userId, Long roleId) {
        Boolean attempt = userService.removeRoleFromUser(userId, roleId);
        return new ResponseEntity<>(attempt, HttpStatus.OK);
    }

//    @PostMapping("/refreshToken")
//    public void refreshToken(HttpServletRequest request, HttpServletResponse response) {
//        String authorizationHeader = request.getHeader(AUTHORIZATION);
//        return new ResponseEntity<>(user, HttpStatus.CREATED); //ok is 201 status code
//    }

    @PostMapping("/add")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        User newUser = userService.addUser(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED); //ok is 201 status code
    }

//    @PutMapping("/resetPassword")
//    public ResponseEntity<Boolean> resetUserPassword(@RequestBody String email) {
//        Boolean attempt = userService.resetUserPassword(email);
//        return new ResponseEntity<>(attempt, HttpStatus.OK);  //ok is 200 status code
//    }

//    @PutMapping("/updatePassword")
//    public ResponseEntity<Boolean> updateUserPassword(@RequestBody User user, String email, String password) {
//        Boolean attempt = userService.updateUserPassword(email);
//        return new ResponseEntity<>(attempt, HttpStatus.OK);  //ok is 200 status code
//    }

    @PutMapping("/update")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        User updateUser = userService.updateUser(user);
        return new ResponseEntity<>(updateUser, HttpStatus.OK);  //ok is 200 status code
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}