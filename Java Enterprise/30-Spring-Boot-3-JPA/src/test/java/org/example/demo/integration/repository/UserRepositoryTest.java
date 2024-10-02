package org.example.demo.integration.repository;

import lombok.AllArgsConstructor;
import org.example.demo.annotation.IntegrationTest;
import org.example.demo.domain.entity.Role;
import org.example.demo.dto.IPersonalInfo;
import org.example.demo.dto.PersonalInfo;
import org.example.demo.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;

@IntegrationTest
@AllArgsConstructor
public class UserRepositoryTest {
    private final UserRepository userRepository;

    @Test
    void checkFindByQuery(){
        var users = userRepository.findAllByFirstNameContainsAndLastNameContains("Aleks","Smith");
        Assertions.assertEquals(2,users.size());

        var users2 = userRepository.findAllByInitials("Aleks","Smith");
        Assertions.assertEquals(2,users2.size());
    }

    @Test
    void checkUpdateRoles(){
        int updatedRows = userRepository.updateRoles(Role.ADMIN,1,4,5);
        Assertions.assertEquals(3,updatedRows);

        var user1 = userRepository.findById(1);
        Assertions.assertEquals(Role.ADMIN,user1.get().getRole());
    }

    @Test
    void checkProjections(){
        List<PersonalInfo> personalInfos = userRepository.findAllByCompanyId(1);
        Assertions.assertEquals(1,personalInfos.size());

        List<PersonalInfo> personalInfos2 = userRepository.findAllByCompanyId(2, PersonalInfo.class);
        Assertions.assertEquals(1,personalInfos2.size());

        List<IPersonalInfo> personalInfos3 = userRepository.findAllByCompanyIdQuery(2);
        Assertions.assertEquals(1,personalInfos3.size());
    }

    @Test
    void findFirstByCompanyIsNotNullOrderByCompanyTest(){
        var user = userRepository.findFirstByCompanyIsNotNullOrderByIdDesc();
        Assertions.assertTrue(user.isPresent());
        user.ifPresent(u->Assertions.assertEquals("Maria",u.getFirstName()));
    }

    @Test
    void findFirst3By(){
        var users = userRepository.findFirst3By(Sort.by("firstName").and(Sort.by("lastName")));
        Assertions.assertEquals(3,users.size());
    }

    @Test
    void checkPageable(){
        var pageable = PageRequest.of(1,2,Sort.by("id"));
        var page = userRepository.findAllBy(pageable);
        Assertions.assertTrue(!page.isEmpty());
        Assertions.assertEquals(2,page.size());
    }
}
