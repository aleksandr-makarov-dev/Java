package org.example.dto;

import org.example.entities.PersonalInfo;
import org.example.entities.Role;

public record UserGetDto(
        Integer id,
        PersonalInfo personalInfo,
        String username,
        Role role,
        CompanyGetDto company
) {
}
