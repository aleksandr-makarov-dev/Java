package org.example.demo.repository;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.ToString;
import org.example.demo.bpp.InjectBean;
import org.example.demo.domain.ConnectionPool;
import org.example.demo.domain.entity.Role;
import org.example.demo.domain.entity.User;
import org.example.demo.dto.IPersonalInfo;
import org.example.demo.dto.PersonalInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    List<User> findAllByFirstNameContainsAndLastNameContains(String firstName, String lastName);


    @Query("select u from User u where u.firstName like %:firstName% and u.lastName like %:lastName%")
    List<User> findAllByInitials(String firstName, String lastName);

    @Modifying(clearAutomatically = true)
    @Query("update User  u set u.role = :role where u.id in (:ids)")
    @Transactional
    int updateRoles(Role role, Integer... ids);

    List<PersonalInfo> findAllByCompanyId(Integer companyId);

    <T> List<T> findAllByCompanyId(Integer companyId, Class<T> clazz);

    @Query("select u.firstName, u.lastName, u.birthDate from User u where u.company.id = :companyId")

    List<IPersonalInfo> findAllByCompanyIdQuery(Integer companyId);

    Optional<User> findFirstByCompanyIsNotNullOrderByIdDesc();

    List<User> findFirst3By(Sort sort);

    List<User> findAllBy(Pageable pageable);
}
