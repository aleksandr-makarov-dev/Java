package org.example.demo.repository;

import lombok.AllArgsConstructor;
import lombok.ToString;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@ToString
public class UserRepository {
    private String userName;
    private int poolSize;
    private List<Object> args;
    private Map<String,Object> properties;

    public void init(){
        System.out.println("Initialize UserRepository");
    }

    public void destroy(){
        System.out.println("Destroy UserRepository");
    }
}
