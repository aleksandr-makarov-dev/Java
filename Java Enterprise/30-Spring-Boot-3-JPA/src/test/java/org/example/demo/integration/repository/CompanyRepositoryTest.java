package org.example.demo.integration.repository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.example.demo.annotation.IntegrationTest;
import org.example.demo.repository.CompanyRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@IntegrationTest
@AllArgsConstructor
@Transactional
public class CompanyRepositoryTest {
    private static final Integer COMPANY_ID = 6;
    private final CompanyRepository companyRepository;

    @Test
    void delete(){
        var maybeCompany = companyRepository.findById(COMPANY_ID);
        Assertions.assertTrue(maybeCompany.isPresent());
        maybeCompany.ifPresent(companyRepository::delete);
        companyRepository.flush();
        Assertions.assertTrue(companyRepository.findById(COMPANY_ID).isEmpty());
    }

    @Test
    void checkFindByQuery(){
        var company1 = companyRepository.findByName("Yandex");

        Assertions.assertNotNull(company1);

        var company2 = companyRepository.findByNameNamedQuery("Yandex");
        Assertions.assertNotNull(company2);

        var company3 = companyRepository.findByNameQuery("Yandex");
        Assertions.assertNotNull(company3);

        var companies = companyRepository.
                findAllByNameContainingIgnoreCase("oo");

        Assertions.assertEquals(2,companies.size());
    }
}
