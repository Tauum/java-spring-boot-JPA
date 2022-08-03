package com.example.javaspringboot.Service.Additional;

import com.example.javaspringboot.Model.Additional.Module;
import com.example.javaspringboot.Model.Additional.ModuleRegisterDto;
import com.example.javaspringboot.Model.User.User;
import com.example.javaspringboot.Repo.Games.ModuleRepo;
import com.example.javaspringboot.Service.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class ModuleService {
    private final ModuleRepo moduleRepo;
    private final UserService userService;

    @Autowired
    public ModuleService(ModuleRepo moduleRepository, UserService userService) {
        this.moduleRepo = moduleRepository;
        this.userService = userService;
    }

    public Module addModule(Module module) { return moduleRepo.save(module); }

    //query method (auto generates method in spring back-backend)
    @Transactional
    public void deleteModule(Long id) { moduleRepo.deleteModuleById(id);}


    public List<ModuleRegisterDto> findAllModulesRegisterDTO() {
        List<Module> initialList =  moduleRepo.findAll();
        List<ModuleRegisterDto> convertedList = new ArrayList<>();
        for (Module initialModule: initialList ) {
            ModuleRegisterDto tempModuleRegisterDto =
                    new ModuleRegisterDto(initialModule.getId(), initialModule.getCode(), initialModule.getName());
            convertedList.add(tempModuleRegisterDto);
        }
        return convertedList;
    }


    public List<ModuleRegisterDto> findModulesRegisterDTOContainingCode(String code) {
        List<Module> initialList =  moduleRepo.findByCodeContains(code);
        List<ModuleRegisterDto> convertedList = new ArrayList<>();
        for (Module initialModule: initialList ) {
            ModuleRegisterDto tempModuleRegisterDto =
                    new ModuleRegisterDto(initialModule.getId(), initialModule.getCode(), initialModule.getName());
            convertedList.add(tempModuleRegisterDto);
        }
        return convertedList;
    }

    public List<ModuleRegisterDto> findAllModulesRegisterDTOForUserId(long id) {
        List<Module> initialList =  moduleRepo.findByStudentsId(id);
        List<ModuleRegisterDto> convertedList = new ArrayList<>();
        for (Module initialModule: initialList ) {
            ModuleRegisterDto tempModuleRegisterDto =
                    new ModuleRegisterDto(initialModule.getId(), initialModule.getCode(), initialModule.getName());
            convertedList.add(tempModuleRegisterDto);
        }
        return convertedList;
    }


    public Module findModuleById(Long id)
    {
        return moduleRepo.findModuleById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entity not found with id: " + id)) ;
    }

    public Module findFirstModuleContainingCode(String code) {
        return moduleRepo.findFirstByCodeContains(code);
    }
    public List<Module> findModuleContainingCode(String code) {
        return moduleRepo.findByCodeContains(code);
    }


    public List<Module> findAllModulesForUser(Long userId){
        List<Module> result = new ArrayList<>();
        User user = userService.findUserById(userId);
        if (user != null){
            for ( Module module : findAll() ){
                if (module.getAdmins().contains(user) || module.getStudents().contains(user)){
                    result.add(module);
                }
            }
            return result;
        }
        else{ return null; }
    }

    public List<Module> findAll() {
        return moduleRepo.findAll();
    }

    public Module addStudentToModule(Long moduleId, Long userId) {
        Module findModule = findModuleById(moduleId);
        User findUser = userService.findUserById(userId);
        if (findModule != null && findUser != null && !findModule.getStudents().contains(findUser) ){
            findModule.addStudent(findUser);
            return moduleRepo.save(findModule);
        }
        return null;
    }

    public Module addAdminToModule(Long moduleId, Long userId) {
        Module findModule = findModuleById(moduleId);
        User findUser = userService.findUserById(userId);
        if (findModule != null && findUser != null && !findModule.getAdmins().contains(findUser)) {
            findModule.addAdmin(findUser);
            return moduleRepo.save(findModule);
        }
        return null;
    }

    public Module removeStudentFromModule(Long moduleId, Long userId) {
        Module findModule = findModuleById(moduleId);
        User findUser = userService.findUserById(userId);
        if (findModule != null && findUser != null && findModule.getStudents().contains(findUser)) {
            findModule.removeStudent(findUser);
            return moduleRepo.save(findModule);
        }
        return null;
    }

    public Module removeAdminFromModule(Long moduleId, Long userId) {
        Module findModule = findModuleById(moduleId);
        User findUser = userService.findUserById(userId);
        if (findModule != null && findUser != null && findModule.getAdmins().contains(findUser)) {
            findModule.removeAdmin(findUser);
            return moduleRepo.save(findModule);
        }
        return null;
    }

//    public List<Module> findAllByUserId(Long id) {
//        return moduleRepo.findAllModulesByUsersId(id);
//    }
}