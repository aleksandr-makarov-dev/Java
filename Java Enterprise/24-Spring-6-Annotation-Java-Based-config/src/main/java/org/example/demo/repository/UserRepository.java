package org.example.demo.repository;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.ToString;
import org.example.demo.bpp.InjectBean;
import org.example.demo.domain.ConnectionPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;


@ToString
public class UserRepository {
//    @InjectBean
//    private ConnectionPool connectionPool;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    @Qualifier("connectionPool1")
    private ConnectionPool connectionPool2;

    // replaces xml init-method
    @PostConstruct
    public void init(){
        System.out.println("Initialize UserRepository");
    }

    // replaces xml destroy-method
    @PreDestroy
    public void destroy(){
        System.out.println("Destroy UserRepository");
    }
}
