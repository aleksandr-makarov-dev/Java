package org.example;

import org.example.entities.User;
import org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy;
import org.hibernate.cfg.Configuration;

import java.time.LocalDate;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        configuration.configure();
        configuration.addAnnotatedClass(User.class);
        // replaces birthDate with birth_date
        configuration.setPhysicalNamingStrategy(new CamelCaseToUnderscoresNamingStrategy());

        var sessionFactory = configuration.buildSessionFactory();

        try(var session = sessionFactory.openSession();){
            session.beginTransaction();

            session.save(User
                            .builder()
                            .username("ivan@gmail.com")
                            .firstname("Ivan")
                            .lastname("Ivanov")
                            .birthDate(LocalDate.of(2000,12,14))
                            .age(23)
                            .build()
            );

            session.getTransaction().commit();
        }
    }
}