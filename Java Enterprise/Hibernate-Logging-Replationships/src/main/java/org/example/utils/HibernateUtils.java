package org.example.utils;

import org.example.converters.BirthdayConverter;
import org.example.entities.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy;
import org.hibernate.cfg.Configuration;

public final class HibernateUtils {
    public static SessionFactory buildSessionFactory(){
        Configuration configuration = new Configuration();
        configuration.configure();
        configuration.addAnnotatedClass(User.class);
        configuration.addAttributeConverter(new BirthdayConverter(),true);
        // replaces birthDate with birth_date
        configuration.setPhysicalNamingStrategy(new CamelCaseToUnderscoresNamingStrategy());

        return configuration.buildSessionFactory();
    }
}
