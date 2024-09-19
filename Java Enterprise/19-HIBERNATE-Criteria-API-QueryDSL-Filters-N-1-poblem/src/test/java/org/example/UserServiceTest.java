package org.example;


import org.example.dao.CompanyRepository;
import org.example.dao.UserRepository;
import org.example.dto.UserCreateDto;
import org.example.entities.*;
import org.example.mapper.CompanyGetMapper;
import org.example.mapper.UserCreateMapper;
import org.example.mapper.UserGetMapper;
import org.example.service.UserService;
import org.example.utils.HibernateUtils;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class UserServiceTest {

    private static final SessionFactory sessionFactory = HibernateUtils.buildSessionFactory();

    @BeforeAll
    public static void beforeAll() {
        try (var session = sessionFactory.openSession()) {
            session.beginTransaction();

            var company1 = Company.builder().name("Yandex").build();

            var company2 = Company.builder().name("Jetbrains").build();

            User user1 = User.builder().username("myuser1@example.com").personalInfo(PersonalInfo.builder().firstname("Pavel").lastname("Ivanov").build()).build();

            company1.addUser(user1);

            User user2 = User.builder().username("user2@example.com").personalInfo(PersonalInfo.builder().firstname("Dmitri").lastname("Popov").build()).build();

            company2.addUser(user2);

            Chat chat = Chat.builder().name("my chat").build();

            session.persist(company1);
            session.persist(company2);
            session.persist(chat);

            session.getTransaction().commit();
        }
    }

    @AfterAll
    public static void afterAll() {
        sessionFactory.close();
    }

    @Test
    public void findById() {
        try (var session = sessionFactory.openSession()) {
            session.beginTransaction();

            CompanyRepository companyRepository = new CompanyRepository(session);
            CompanyGetMapper companyMapper = new CompanyGetMapper();

            UserGetMapper userMapper = new UserGetMapper(companyMapper);
            UserCreateMapper userCreateMapper = new UserCreateMapper(companyRepository);

            UserRepository userRepository = new UserRepository(session);
            UserService userService = new UserService(userRepository, userMapper, userCreateMapper);

            var createdId = userService.create(
                    new UserCreateDto(
                            PersonalInfo.builder()
                                    .firstname("Bob")
                                    .lastname("Ross")
                                    .build(),
                            "bob.ross@example.com",
                            Role.USER,
                            1
                    )
            );

            userService.findById(createdId).ifPresent(System.out::println);

            session.getTransaction().commit();
        }
    }
}
