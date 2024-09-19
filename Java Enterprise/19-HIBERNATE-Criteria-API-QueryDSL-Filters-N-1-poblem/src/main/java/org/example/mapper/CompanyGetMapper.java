package org.example.mapper;

import org.example.dto.CompanyGetDto;
import org.example.entities.Company;

public class CompanyGetMapper implements Mapper<Company, CompanyGetDto> {
    @Override
    public CompanyGetDto mapFrom(Company from) {
        return  new CompanyGetDto(
                from.getId(),
                from.getName()
        );
    }
}
