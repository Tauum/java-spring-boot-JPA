package com.example.javaspringboot.Model.User;

import com.example.javaspringboot.Model.Additional.ModuleRegisterDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserProfile {
    private long id;

    private String email;

    private String password;

    private String userInstitutionId;

    private String firstName;

    private String lastName;

    private Date createdOn;

    private Date updatedOn;

    private LocalDate dob;

    private boolean termsAndConditions = false;

    private boolean initialRegister = false;

    @Nullable
    private Integer avatar;

    private boolean enabled=true;

    private Set<Role> roles = new HashSet<>();

    private List<ModuleRegisterDto> modules;
}
