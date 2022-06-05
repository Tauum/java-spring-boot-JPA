package com.example.javaspringboot.Service;

import com.example.javaspringboot.Model.Role;
import com.example.javaspringboot.Model.User;
import com.example.javaspringboot.Repository.RoleRepository;
import com.example.javaspringboot.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Service @RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepo;
    private final RoleRepository roleRepo;
    private final PasswordEncoder passwordEncoder;


    @Override // THIS OVERWRITES THE DEFAULT SPRING SECURITY ONE
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findUserByEmail(username);
        if ( user != null){
            Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
            user.getRoles().forEach(role -> { authorities.add(new SimpleGrantedAuthority(role.getName()));});
            return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
        }
        else{ throw new EntityNotFoundException("Entity not found"); }
    }

    public User addUser(User user) {

//        THIS NEEDS COMMENTING OUT WHEN INSERTED PASSWORD ENCODING FROMTEND
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepo.save(user);
    }

    public List<User> findAll() {
        return userRepo.findAll();
    }

    public Boolean addRoleToUser(Long userId, Long roleId){
        User findUser = findUserById(userId);
        Role findRole = roleRepo.findRoleById(roleId);
        if (findUser != null && findRole != null && !findUser.getRoles().contains(findRole)) {
            try {
                findUser.getRoles().add(findRole);
                userRepo.save(findUser);
                return true;
                }
             catch (Exception e) { throw new UnknownError("Error attempting to save");  }
        }
         throw new EntityNotFoundException("Entity not found");
    }

    public Boolean removeRoleFromUser(Long userId, Long roleId){
        User findUser = findUserById(userId);
        Role findRole = roleRepo.findRoleById(roleId);
        if (findUser != null && findRole != null && findUser.getRoles().contains(findRole)) {
            try {
                findUser.getRoles().remove(findRole);
                userRepo.save(findUser);
                return true;
            }
            catch (Exception e) { throw new UnknownError("Error attempting to save");  }
        }
        throw new EntityNotFoundException("Entity not found");
    }

    public User updateUser(User user) {

        User find = findUserById(user.getId());

        if (find != null) {
            try {
                if (user.getFirstName() != null && user.getFirstName().length() > 1 && user.getFirstName() != find.getFirstName()) {
                    find.setFirstName(user.getFirstName());
                }
            } catch (Exception e) {
            }

            try {
                if (user.getLastName() != null && user.getLastName().length() > 1 && user.getLastName() != find.getLastName()) {
                    find.setLastName(user.getLastName());
                }
            } catch (Exception e) {
            }

            try {
                if (user.getEmail() != null && user.getEmail() != find.getEmail() && user.getEmail().length() > 3) {
                    find.setEmail(user.getEmail());
                }
            } catch (Exception e) {
            }

//            try {
//                if (user.getDob() != null && user.getDob() != find.getDob()){
//                    find.setDob(user.getDob());
//                }
//            }
//            catch (Exception e){ }

            try {
                if (user.getRoles() != null && user.getRoles() != find.getRoles()) {
                    find.setRoles(user.getRoles());
                }
            } catch (Exception e) {
            }

            // REDO THIS BLOCK BELOW TO UPDATE THE USERS STUDYING MODULES AND ADMIN MODULES
//            try {
//                if (user.getModules() != null && user.getModules() != find.getModules()){
//                    find.setModules(user.getModules());
//                }
//            }
//            catch (Exception e){ }

            return userRepo.save(find);
        }

        return null;
    }


    //query method (auto generates method in spring back-backend)
    @Transactional
    public void deleteUser(Long id) {
        userRepo.deleteUserById(id);
    }

    public User findUserById(Long id) {
        User user = userRepo.findUserById(id);
        if (user != null) {
            return user;
        } else {
            throw new EntityNotFoundException("Entity not found with ID: " + id);
        }
    }


    public User findUserByEmail(String email) { // < this is trying to get a user and puit it in a bool

        User user = userRepo.findUserByEmail(email);
        if (user != null) { return user; }
        else { throw new EntityNotFoundException("Entity not found with Email: " + email); }
    }

    public List<User> findByRoleContains(Long roleId) {
        Role findRole = roleRepo.findRoleById(roleId);
        if (findRole != null){
            return userRepo.findAllByRolesContains(findRole);
        }
        else { throw new EntityNotFoundException("Entity not found with Role ID: " + roleId); }

    }
    //fix this to not be plain text and more complex
    public Boolean resetUserPassword(String email) {
        User findUser = userRepo.findUserByEmail(email);

        if (findUser != null && findUser.getLastName() != null){
            findUser.setPassword(findUser.getLastName());
            userRepo.save(findUser);
            return true;
        }
        throw new EntityNotFoundException("An error occured and the password was not reset");
    }

    //fix this to not be plain text and more complex
    public Boolean updatetUserPassword(User requestingUser, String email, String password) {
        User findUser = userRepo.findUserByEmail(email);
        if(findUser != null && requestingUser == findUser && findUser.getPassword() != password && password.length() > 6){
            findUser.setPassword(password);
            userRepo.save(findUser);
            return true;
        }
        throw new EntityNotFoundException("An error occured and the password was not updated");
    }
}
//    public List<User> findUsersByModule(Module passedModule){
//        return userRepo.findAllByStudentModulesContains(passedModule);
//    }