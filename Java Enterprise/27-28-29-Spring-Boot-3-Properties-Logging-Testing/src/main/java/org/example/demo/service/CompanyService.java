package org.example.demo.service;

import org.example.demo.dto.CompanyReadDto;
import org.example.demo.listener.AccessType;
import org.example.demo.listener.EntityEvent;
import org.example.demo.repository.CompanyRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CompanyService {
    private final CompanyRepository companyRepository;
    private final ApplicationEventPublisher applicationEventPublisher;

    public CompanyService(CompanyRepository companyRepository, ApplicationEventPublisher applicationEventPublisher) {
        this.companyRepository = companyRepository;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public Optional<CompanyReadDto> findById(Integer id){
        return companyRepository.findById(id)
                        .map(entity->{
                            applicationEventPublisher.publishEvent(new EntityEvent(entity, AccessType.READ));
                            return new CompanyReadDto(entity.getId());
                        });
    }
}
