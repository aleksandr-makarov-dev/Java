package org.example;

import jakarta.persistence.Column;
import jakarta.persistence.Table;
import lombok.Cleanup;
import org.example.entities.*;
import org.example.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    private static final SessionFactory sessionFactory = HibernateUtils.buildSessionFactory();

    @Test
    public void testHibernateApi() throws SQLException, IllegalAccessException {

        // how hibernate works
        var user = User
                .builder()
                .username("ivan1@gmail.com")
                .personalInfo(
                        PersonalInfo
                                .builder()
                                .firstname("Ivan")
                                .lastname("Ivanov")
                                .birthDate(new Birthday(LocalDate.of(2000,12,14)))
                                .build()
                )
                .build();

        var sql = """
                insert into
                %s
                (%s)
                values
                (%s)
                """;
        // getting table name from Table annotation. If no annotation found, take class name

        var tableName = Optional.ofNullable(user.getClass().getAnnotation(Table.class))
                .map(table->table.schema() + "." + table.name())
                .orElse(user.getClass().getName());

        Field[] fields = user.getClass().getDeclaredFields();

        // getting field names
        var columnNames = Arrays.stream(fields)
                .map(field -> Optional.ofNullable(field.getAnnotation(Column.class))
                        .map(Column::name)
                        .orElse(field.getName()))
                .collect(Collectors.joining(", "));

        var columnValues = Arrays.stream(fields)
                .map(field -> "?")
                .collect(Collectors.joining(", "));

        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","root");

        PreparedStatement preparedStatement = connection.prepareStatement(sql.formatted(tableName,columnNames,columnValues));

        for (int i = 0; i<fields.length; i++){
            fields[i].setAccessible(true);
            preparedStatement.setObject(i+1,fields[i].get(user));
        }

        System.out.println(preparedStatement);

        preparedStatement.executeUpdate();

        preparedStatement.close();

        connection.close();
    }

    @Test
    public void testSessionFactory(){
        try(var session = sessionFactory.openSession()){
            session.beginTransaction();

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
                    .build();

            session.saveOrUpdate(user);

            session.detach(user);

            session.getTransaction().commit();
        }
    }

    @Test
    public void checkOneToMany(){
        try(var sessionFactory = HibernateUtils.buildSessionFactory();
            var session = sessionFactory.openSession()){

            session.beginTransaction();

            var company = session.get(Company.class,1);

            System.out.println(company.getUsers());

            session.getTransaction().commit();
        }
    }

    @Test
    public void AddNewUserAndCompany(){
        try(var sessionFactory = HibernateUtils.buildSessionFactory();
            var session = sessionFactory.openSession()){
            session.beginTransaction();

            Company company = Company
                    .builder()
                    .name("Yandex")
                    .build();

            User user = User
                    .builder()
                    .username("myuser123@example.com")
                    .build();

            company.addUser(user);

            session.persist(company);

            session.getTransaction().commit();
        }
    }

    @Test
    public void checkOrphalRemove(){
        try(var sessionFactory = HibernateUtils.buildSessionFactory();
            var session = sessionFactory.openSession()){
            session.beginTransaction();

            Company company = session.get(Company.class, 3);
            company.getUsers().removeIf(user -> user.getId().equals(5));

            session.getTransaction().commit();
        }
    }

    @Test
    public void checkOneToOne(){
        try(var sessionFactory = HibernateUtils.buildSessionFactory();
            var session = sessionFactory.openSession()){
            session.beginTransaction();

            User user = User
                    .builder()
                    .username("userwithprofile@example.com")
                    .build();

            Profile profile = Profile
                    .builder()
                    .language("RU")
                    .address("Pobedy 1")
                    .build();

            session.persist(user);
            profile.setUser(user);
            session.persist(profile);

            session.getTransaction().commit();
        }
    }

    @Test
    public void checkManyToMany(){
        try(var sessionFactory = HibernateUtils.buildSessionFactory();
            var session = sessionFactory.openSession()){
            session.beginTransaction();

//            Chat chat = Chat
////                    .builder()
////                    .name("mychat")
////                    .build();
////
////            User user = session.get(User.class,4);
////            user.addChat(chat);
////
////            session.persist(chat);

            Chat chat = session.get(Chat.class,1);
            User user = session.get(User.class,4);

            UserChat userChat = UserChat
                    .builder()
                    .createdAt(Instant.now())
                    .createdBy("Alex")
                    .build();

            userChat.setChat(chat);
            userChat.setUser(user);

            session.persist(userChat);

            session.getTransaction().commit();
        }
    }

    @Test
    public void checkH2(){
        try(var sessionFactory = HibernateUtils.buildSessionFactory();
            var session = sessionFactory.openSession()){
            session.beginTransaction();

            var company = Company.builder()
                    .name("My company")
                    .build();

            session.persist(company);

            session.getTransaction().commit();
        }
    }
}