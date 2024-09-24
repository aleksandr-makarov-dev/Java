package org.example.demo.repository;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.ToString;
import org.example.demo.bpp.InjectBean;
import org.example.demo.domain.ConnectionPool;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

//@Repository
@ToString
public class UserRepositoryCustomInjection {

//    custom injection bean
    @InjectBean
    private ConnectionPool connectionPool;

    @Value("${db.pool.size}")
    private Integer poolSize;


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
