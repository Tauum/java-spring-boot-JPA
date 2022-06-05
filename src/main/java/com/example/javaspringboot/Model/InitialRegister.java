package com.example.javaspringboot.Model;


//import com.example.javaspringboot.Model.Additional.ModuleRegisterDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InitialRegister {

    private String firstName;
    private String lastName;
    @Nullable
    private String userInstitutionId;
    @Nullable
    private Integer avatar;

//    @Nullable
//    private List<ModuleRegisterDto> moduleRegisterDtoList;

    private boolean initialRegister;
    private boolean student;

}
