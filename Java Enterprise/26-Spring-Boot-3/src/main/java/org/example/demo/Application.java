package org.example.demo;

import org.example.demo.repository.UserRepository;
import org.example.demo.service.CompanyService;
import org.example.demo.service.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
//        SpringApplication.run(Application.class, args);

//        var context = new ClassPathXmlApplicationContext("application.xml");
//        xml -> annotations
        var context = new AnnotationConfigApplicationContext(Application.class);
        var companyService = context.getBean(CompanyService.class);
        companyService.findById(1);

        context.close();
    }
}
