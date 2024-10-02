package org.example.demo;

import org.example.demo.config.DatabaseProperties;
import org.example.demo.domain.ConnectionPool;
import org.example.demo.repository.UserRepository;
import org.example.demo.service.CompanyService;
import org.example.demo.service.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.SpringProperties;

@SpringBootApplication
@ConfigurationPropertiesScan
public class Application {

    public static void main(String[] args) {
        var context = SpringApplication.run(Application.class, args);
    }
}
