package org.example.demo.repository;

import org.example.demo.domain.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {

    Optional<Company> findByName(String name);

    Optional<Company> findByNameNamedQuery(String name);

    @Query("select c from Company c join fetch c.locales cl where c.name = :name2")
    Optional<Company> findByNameQuery(@Param("name2") String name);

    List<Company> findAllByNameContainingIgnoreCase(String fragment);
}
