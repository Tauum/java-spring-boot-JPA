package com.example.javaspringboot.Additional.Controller;

import com.example.javaspringboot.Additional.Model.Module;
import com.example.javaspringboot.Additional.Model.ModuleRegisterDto;
import com.example.javaspringboot.Additional.Service.ModuleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Modules")
public class ModuleController {
    private final ModuleService moduleService;

    public ModuleController(ModuleService moduleService) {this.moduleService = moduleService; }

    @GetMapping
    public ResponseEntity<List<Module>> getAllModules()
    {
        List<Module> modules = moduleService.findAll();
        return new ResponseEntity<>(modules, HttpStatus.OK); //ok is 200 status code
    }

    @GetMapping("/getAllModuleRegisterDTO")
    public ResponseEntity<List<ModuleRegisterDto>> getAllModulesRegisterDTO()
    {
        List<ModuleRegisterDto> modules = moduleService.findAllModulesRegisterDTO();
        return new ResponseEntity<>(modules, HttpStatus.OK); //ok is 200 status code
    }

    @GetMapping("/getModuleRegisterDTO/byCode/{code}")
    public ResponseEntity<List<ModuleRegisterDto>> getModulesRegisterDTOByCode(@PathVariable("code") String code)
    {
        List<ModuleRegisterDto> modules = moduleService.findModulesRegisterDTOContainingCode(code);
        return new ResponseEntity<>(modules, HttpStatus.OK); //ok is 200 status code
    }

    @GetMapping("/byCode/{code}")
    public ResponseEntity<List<Module>> getModuleByCode(@PathVariable("code") String code)
    {
        List<Module> modules = moduleService.findModuleContainingCode(code);
        return new ResponseEntity<>(modules, HttpStatus.OK); //ok is 200 status code
    }

    @GetMapping("/{id}")
    public ResponseEntity<Module> getModuleByID(@PathVariable("id") Long id)
    {
        Module module = moduleService.findModuleById(id);
        return new ResponseEntity<>(module, HttpStatus.OK); //ok is 200 status code
    }


    @GetMapping("/ForUser/{id}")
    public ResponseEntity<List<Module>> getForUser(@PathVariable("id") Long id)
    {
        List<Module> modules = moduleService.findAllModulesForUser(id);
        return new ResponseEntity<>(modules, HttpStatus.OK); //ok is 200 status code
    }

    @PutMapping("/AddStudentToModule/{moduleId}/{userId}")
    public ResponseEntity<Module> addStudentToModule(@PathVariable("moduleId") Long moduleId, @PathVariable("userId") Long userId)
    {
        Module attempt = moduleService.addStudentToModule(moduleId, userId);
        return new ResponseEntity<>(attempt, HttpStatus.OK); //ok is 200 status code
    }

    @PutMapping("/AddAdminToModule/{moduleId}/{userId}")
    public ResponseEntity<Module> addAdminToModule(@PathVariable("moduleId") Long moduleId, @PathVariable("userId") Long userId)
    {
        Module attempt = moduleService.addAdminToModule(moduleId, userId);
        return new ResponseEntity<>(attempt, HttpStatus.OK); //ok is 200 status code
    }

    @PutMapping("/RemoveStudentFromModule/{moduleId}/{userId}")
    public ResponseEntity<Module> RemoveStudentFromModule(@PathVariable("moduleId") Long moduleId, @PathVariable("userId") Long userId)
    {
        Module attempt = moduleService.removeStudentFromModule(moduleId, userId);
        return new ResponseEntity<>(attempt, HttpStatus.OK); //ok is 200 status code
    }

    @PutMapping("/RemoveAdminFromModule/{moduleId}/{userId}")
    public ResponseEntity<Module> RemoveAdminFromModule(@PathVariable("moduleId") Long moduleId, @PathVariable("userId") Long userId)
    {
        Module attempt = moduleService.removeAdminFromModule(moduleId, userId);
        return new ResponseEntity<>(attempt, HttpStatus.OK); //ok is 200 status code
    }

//    @GetMapping("/GetAllForUser/{id}")
//    public ResponseEntity<List<Module>> GetAllForUser(@PathVariable("id") Long id){
//        List<Module> modules = moduleService.findAllByUserId(id);
//        return new ResponseEntity<>(modules, HttpStatus.CREATED); //ok is 200 status code
//    }

//    @GetMapping("/GetAllUsersFor/{id}")
//    public ResponseEntity<List<Module>> GetAllUsersFor(@PathVariable("id") Long id){
//        List<Module> modules = moduleService.findAllUsersByModuleId(id);
//        return new ResponseEntity<>(modules, HttpStatus.CREATED); //ok is 200 status code
//    }
//

    @PostMapping("/add")
    public ResponseEntity<Module> addModule(@RequestBody Module module)
    {
        Module newModule = moduleService.addModule(module);
        return new ResponseEntity<>(module, HttpStatus.CREATED); //ok is 200 status code
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteModule(@PathVariable("id") Long id)
    {
        Module attempt = moduleService.findModuleById(id);
        moduleService.deleteModule(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

