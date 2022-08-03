package com.example.javaspringboot.Controller.User;


import com.example.javaspringboot.Model.User.EnumRole;
import com.example.javaspringboot.Model.User.MyUserDetails;
import com.example.javaspringboot.Model.User.Role;
import com.example.javaspringboot.Model.User.User;
import com.example.javaspringboot.Security.Request.JwtUtils;
import com.example.javaspringboot.Repo.User.RoleRepository;
import com.example.javaspringboot.Repo.User.UserRepository;
import com.example.javaspringboot.Security.Response.MessageResponse;
import com.example.javaspringboot.Security.Response.UserInfoResponse;
import com.example.javaspringboot.Service.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import java.util.*;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserService userService;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody User loginRequest) {

        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())); // gets error here

        SecurityContextHolder.getContext().setAuthentication(authentication);
        MyUserDetails userDetails = (MyUserDetails) authentication.getPrincipal();
        ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);
        List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority()).collect(Collectors.toList());

        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                .body(userService.findUserProfileUserByEmail(userDetails.getEmail()));
//                .body(new UserInfoResponse(userDetails.getId(), userDetails.getEmail(), roles));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody User signUp) {

        if(userService.findUserByEmail(signUp.getEmail()) != null){
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
        }

//        Set<Role> roles = new HashSet<>();
//        User user = new User(signUp.getEmail(), encoder.encode(signUp.getPassword()), signUp.getDob(), signUp.getCreatedOn(), signUp.isTermsAndConditions() );
//
//        Role undefined = roleRepository.findByName(EnumRole.ROLE_UNDEFINED);
//        if (undefined == null) { roleRepository.save(new Role(EnumRole.ROLE_UNDEFINED)); }
//        roles.add(undefined);
//        user.setRoles(roles);
        userService.addUser(signUp);
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    @PostMapping("/signout")
    public ResponseEntity<?> logoutUser() { // something is throwing an error here on sign out
        ResponseCookie cookie = jwtUtils.getCleanJwtCookie();
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body(new MessageResponse("You've been signed out!"));
    }
}
