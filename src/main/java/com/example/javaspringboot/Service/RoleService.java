package com.example.javaspringboot.Service;
import com.example.javaspringboot.Model.Role;
import com.example.javaspringboot.Model.User;
import com.example.javaspringboot.Repository.RoleRepository;
import com.example.javaspringboot.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;

@Service @RequiredArgsConstructor
public class RoleService{
    private final RoleRepository roleRepo;
    private final UserRepository userRepo;



    public Role addRole(Role Role) {
        return roleRepo.save(Role);
    }

    public List<Role> findAll() {
        return roleRepo.findAll();
    }

    public Role findRoleById(Long id) {
        Role Role = roleRepo.findRoleById(id);
        if (Role != null) {  return Role; }
        else { throw new EntityNotFoundException("Entity not found with ID: " + id);  }
    }

    public Role findRoleByName(String name) { // < this is trying to get a Role and puit it in a bool

        Role Role = roleRepo.findRoleByName(name);
        if (Role != null) { return Role; }
        else { throw new EntityNotFoundException("Entity not found with Name: " + name); }
    }

    public Role updateRole(Role Role) {
        Role find = findRoleById(Role.getId());
        if (find != null) {
            try {
                if (Role.getName() != null && Role.getName().length() > 1) {
                    find.setName(Role.getName());
                }
            } catch (Exception e) {  }
            return roleRepo.save(find);
        }
        return null;
    }

    //query method (auto generates method in spring back-backend)
    @Transactional
    public void deleteRole(Long id) {
        roleRepo.deleteRoleById(id);
    }


}