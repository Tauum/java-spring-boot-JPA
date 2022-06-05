package com.example.javaspringboot.Service;
import com.example.javaspringboot.Model.User;
import com.example.javaspringboot.Model.MyUserDetails;
import com.example.javaspringboot.Repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) { return user.map(MyUserDetails::new).get(); }
        return null;
    }

//    public User addUser(User user){
//        if (loadUserByUsername(user.getUserName()) == null){ return userRepository.save(user); }
//        return null;
//    }
}