package org.example;


import org.example.dao.UserDao;
import org.example.entities.Chat;
import org.example.entities.Company;
import org.example.entities.PersonalInfo;
import org.example.entities.User;
import org.example.utils.HibernateUtils;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

public class UserDaoTest {

    private static final SessionFactory sessionFactory = HibernateUtils.buildSessionFactory();
    private final UserDao userDao = UserDao.getInstance();

    @BeforeAll
    public static void beforeAll(){
        try(var session = sessionFactory.openSession()){
            session.beginTransaction();

            var company1 = Company.builder()
                    .name("Yandex")
                    .build();

            var company2 = Company.builder()
                    .name("Jetbrains")
                    .build();

            User user1 = User
                    .builder()
                    .username("myuser1@example.com")
                    .personalInfo(PersonalInfo
                            .builder()
                            .firstname("Pavel")
                            .lastname("Ivanov")
                            .build()
                    )
                    .build();

            company1.addUser(user1);

            User user2 = User
                    .builder()
                    .username("user2@example.com")
                    .personalInfo(PersonalInfo
                            .builder()
                            .firstname("Dmitri")
                            .lastname("Popov")
                            .build()
                    )
                    .build();

            company2.addUser(user2);

            Chat chat = Chat
                    .builder()
                    .name("my chat")
                    .build();

            session.persist(company1);
            session.persist(company2);
            session.persist(chat);

            session.getTransaction().commit();
        }
    }

    @AfterAll
    public static void afterAll(){
        sessionFactory.close();
    }

    @Test
    void findAll(){
        try(var session = sessionFactory.openSession()){
            session.beginTransaction();

            List<User> users = userDao.findAll(session);

            session.getTransaction().commit();
        }
    }

    @Test
    void findAllByFirstName(){
        try(var session = sessionFactory.openSession()){
            session.beginTransaction();

            List<User> users = userDao.findAllByFirstName(session,"Pavel");

            System.out.println("Found users:");
            for(var user : users){
                System.out.println(user);
            }

            session.getTransaction().commit();
        }
    }
}
