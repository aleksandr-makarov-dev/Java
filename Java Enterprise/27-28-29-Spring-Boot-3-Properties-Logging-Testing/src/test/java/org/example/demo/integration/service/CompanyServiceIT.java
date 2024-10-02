package org.example.demo.integration.service;

import lombok.RequiredArgsConstructor;
import org.example.demo.annotation.IntegrationTest;
import org.example.demo.config.DatabaseProperties;
import org.example.demo.dto.CompanyReadDto;
import org.example.demo.service.CompanyService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.TestConstructor;

@IntegrationTest
@RequiredArgsConstructor
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
public class CompanyServiceIT {

    private static final Integer COMPANY_ID = 1;

    private final CompanyService companyService;
    private final DatabaseProperties databaseProperties;

    @Test
    void findById(){
        var actualResult = companyService.findById(COMPANY_ID);

        Assertions.assertTrue(actualResult.isPresent());

        var expectedResult = new CompanyReadDto(COMPANY_ID);
        actualResult.ifPresent(actual-> Assertions.assertEquals(expectedResult, actual));
    }
}
