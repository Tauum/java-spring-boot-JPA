package com.example.javaspringboot.Controller.User;

import com.example.javaspringboot.Model.User.InitialRegister;
import com.example.javaspringboot.Model.User.User;
import com.example.javaspringboot.Model.User.UserProfile;
import com.example.javaspringboot.Service.User.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

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


    @PostMapping("/getUserProfileByEmail")
    public ResponseEntity<UserProfile> getUserProfileByEmail(@RequestBody User user) {
        UserProfile attempt = userService.findUserProfileUserByEmail(user.getEmail());
        return new ResponseEntity<>(attempt, HttpStatus.OK);
    }

    @PostMapping("/getByEmail")
    public ResponseEntity<User> getUserByEmail(@RequestBody User user) {
        User attempt = userService.findUserByEmail(user.getEmail());
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

    @PostMapping("/add")
    public ResponseEntity<?> addUser(@RequestBody User user) {
        if (userService.addUser(user)){  return new ResponseEntity<>(HttpStatus.CREATED); }
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    @PutMapping("/initialRegister/{id}")
    public ResponseEntity<?> addUser(@RequestBody InitialRegister initialRegister, @PathVariable("id") Long id) {
        boolean result = false;
        if (userService.initialRegister(initialRegister, id)){ return new ResponseEntity<>(result,HttpStatus.OK); }
        else{ return new ResponseEntity<>(result,HttpStatus.NOT_MODIFIED); }
    }

    @PutMapping("/resetPassword")
    public ResponseEntity<Boolean> resetUserPassword(@RequestBody String email) {
        Boolean attempt = userService.resetUserPassword(email);
        return new ResponseEntity<>(attempt, HttpStatus.OK);  //ok is 200 status code
    }

    @PutMapping("/updatePassword")
    public ResponseEntity<Boolean> updateUserPassword(@RequestBody User user, String newPassword) {
        Boolean attempt = userService.updatetUserPassword(user, newPassword);
        return new ResponseEntity<>(attempt, HttpStatus.OK);  //ok is 200 status code
    }

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
