package org.example;

import org.example.entities.Birthday;
import org.example.entities.PersonalInfo;
import org.example.entities.Role;
import org.example.entities.User;
import org.example.utils.HibernateUtils;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import java.time.LocalDate;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {

        var sessionFactory = HibernateUtils.buildSessionFactory();

        try(var session = sessionFactory.openSession();){
            session.beginTransaction();

            User user = User
                    .builder()
                    .username("ivan123@gmail.com")
                    .personalInfo(PersonalInfo
                            .builder()
                            .firstname("Ivan")
                            .lastname("Ivanov")
                            .birthDate(new Birthday(LocalDate.of(2000,12,14)))
                            .build()
                    )
                    .role(Role.ADMIN)
                    .build();

            logger.info("User object in transient state {}", user);

            // creates new user
//            session.save(user);

            // updates user
//            session.update(user);

            // creates if not exists or updates

            session.saveOrUpdate(user);
            logger.info("User was saved");

            // delete user
//            session.delete(user);
//            logger.debug("User deleted");

            session.getTransaction().commit();
        } catch (Exception e) {
            logger.error("Exception occurred: ", e);
            throw  e;
        }
    }
}