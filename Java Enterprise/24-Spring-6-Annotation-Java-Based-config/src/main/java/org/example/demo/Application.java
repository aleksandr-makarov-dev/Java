package org.example.demo;

import org.example.demo.repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
//        SpringApplication.run(Application.class, args);

        var context = new ClassPathXmlApplicationContext("application.xml");
        var userRepository = context.getBean(UserRepository.class);

        System.out.println(userRepository);

        context.close();
    }
}
