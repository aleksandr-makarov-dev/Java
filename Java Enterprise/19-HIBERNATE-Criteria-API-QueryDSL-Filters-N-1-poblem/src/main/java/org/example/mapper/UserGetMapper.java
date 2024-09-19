package org.example.mapper;

import lombok.RequiredArgsConstructor;
import org.example.dto.UserGetDto;
import org.example.entities.User;

@RequiredArgsConstructor
public class UserGetMapper implements Mapper<User, UserGetDto> {
    private final CompanyGetMapper companyGetDtoMapper;

    @Override
    public UserGetDto mapFrom(User from) {
         return new UserGetDto(
                 from.getId(),
                 from.getPersonalInfo(),
                 from.getUsername(),
                 from.getRole(),
                 companyGetDtoMapper.mapFrom(from.getCompany())
         );
    }
}
