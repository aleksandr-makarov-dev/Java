package org.example;

import org.example.entities.*;
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

            //        Company company = Company
//                .builder()
//                .name("Mail")
//                .build();

            var company = session.get(Company.class,1);

            User user = User
            .builder()
            .username("alex123@gmail.com")
            .personalInfo(PersonalInfo
                    .builder()
                    .firstname("Alex")
                    .lastname("Johnson")
                    .birthDate(new Birthday(LocalDate.of(2000,12,14)))
                    .build()
            )
            .role(Role.ADMIN)
            .company(company)
            .build();

            logger.info("User object in transient state {}", user);

            // creates new user
//            session.save(user);

            // updates user
//            session.update(user);

            // creates if not exists or updates

//            session.saveOrUpdate(company);

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