package org.example;

import jakarta.persistence.Column;
import jakarta.persistence.Table;
import org.example.entities.Birthday;
import org.example.entities.PersonalInfo;
import org.example.entities.Role;
import org.example.entities.User;
import org.example.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
}