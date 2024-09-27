package org.example.demo.repository;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.AllArgsConstructor;
import lombok.ToString;
import org.example.demo.bpp.InjectBean;
import org.example.demo.domain.ConnectionPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@ToString
public class UserRepository {
    private final ConnectionPool connectionPool;

    @Value("${db.pool.size}")
    private Integer poolSize;

    public UserRepository(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

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
