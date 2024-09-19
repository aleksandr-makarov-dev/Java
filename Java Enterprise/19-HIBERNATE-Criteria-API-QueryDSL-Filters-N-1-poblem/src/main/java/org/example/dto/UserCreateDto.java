package org.example.dto;

import org.example.entities.PersonalInfo;
import org.example.entities.Role;

public record UserCreateDto(
        PersonalInfo personalInfo,
        String username,
        Role role,
        Integer companyId
){
}
