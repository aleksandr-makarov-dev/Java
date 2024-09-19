package org.example.mapper;

import lombok.RequiredArgsConstructor;
import org.example.dao.CompanyRepository;
import org.example.dto.UserCreateDto;
import org.example.entities.User;

@RequiredArgsConstructor
public class UserCreateMapper implements Mapper<UserCreateDto, User>{
    private final CompanyRepository companyRepository;

    @Override
    public User mapFrom(UserCreateDto from) {
        return  User.builder()
                .personalInfo(from.personalInfo())
                .username(from.username())
                .role(from.role())
                .company(companyRepository.findById(from.companyId()).orElseThrow(IllegalArgumentException::new))
                .build();
    }
}
