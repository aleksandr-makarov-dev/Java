package org.example.demo.domain;

import jakarta.annotation.PostConstruct;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@ToString
public class ConnectionPool {

    private String username;
    private String password;
    private int poolSize;
    private String url;

    public ConnectionPool(
            @Value("${db.username}") String username,
            @Value("${db.password}") String password,
            @Value("${db.pool.size}") int poolSize,
            @Value("${db.url}") String url
    ) {
        this.username = username;
        this.password = password;
        this.poolSize = poolSize;
        this.url = url;
    }


    @PostConstruct
    public void init(){
        log.info("Connection pool initialized");

        System.out.println("Init connection pool");
    }
//    private List<Object> args;
//    private Map<String,Object> properties;
}
