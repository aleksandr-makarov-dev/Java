package org.example;

import org.example.converters.BirthdayConverter;
import org.example.entities.Birthday;
import org.example.entities.Role;
import org.example.entities.User;
import org.example.utils.HibernateUtils;
import org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy;
import org.hibernate.cfg.Configuration;

import java.time.LocalDate;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {


        var sessionFactory = HibernateUtils.buildSessionFactory();

        try(var session = sessionFactory.openSession();){
            session.beginTransaction();

            User user =User
                            .builder()
                            .username("ivan123@gmail.com")
                            .firstname("Ivan")
                            .lastname("Ivanov")
                            .birthDate(new Birthday(LocalDate.of(2000,12,14)))
                            .role(Role.ADMIN)
                            .build();
            // creates new user
//            session.save(user);

            // updates user
//            session.update(user);

            // creates if not exists or updates

            session.saveOrUpdate(user);

            User user1 = session.get(User.class,"ivan123@gmail.com");
            User user2 = session.get(User.class,"ivan123@gmail.com");
            User user3 = session.get(User.class,"ivan123@gmail.com");

            System.out.println(user1.toString());

            // delete user
            session.delete(user);

            session.getTransaction().commit();
        }
    }
}