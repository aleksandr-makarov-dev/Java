package org.example.demo.unit;

import org.example.demo.domain.entity.Company;
import org.example.demo.dto.CompanyReadDto;
import org.example.demo.listener.EntityEvent;
import org.example.demo.repository.CompanyRepository;
import org.example.demo.service.CompanyService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;


import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class CompanyServiceTest {

    private static final Integer COMPANY_ID = 1;

    @Mock
    private CompanyRepository companyRepository;
    @Mock
    private ApplicationEventPublisher applicationEventPublisher;
    @InjectMocks
    private CompanyService companyService;

    @Test
    void findById(){
        Mockito.doReturn(Optional.of(new Company(COMPANY_ID)))
                .when(companyRepository).findById(COMPANY_ID);

        var actualResult = companyService.findById(COMPANY_ID);

        Assertions.assertTrue(actualResult.isPresent());

        var expectedResult = new CompanyReadDto(COMPANY_ID);
        actualResult.ifPresent(actual-> Assertions.assertEquals(expectedResult, actual));

        Mockito.verify(applicationEventPublisher).publishEvent(Mockito.any(EntityEvent.class));
    }
}
