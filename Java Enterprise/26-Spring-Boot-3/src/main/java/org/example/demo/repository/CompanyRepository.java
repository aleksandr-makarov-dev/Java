package org.example.demo.repository;

import org.example.demo.domain.entity.Company;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class CompanyRepository {
    public Optional<Company> findById(Integer id){
        System.out.println("Company repository findById Method");

        return Optional.of(new Company(id));
    }
}
