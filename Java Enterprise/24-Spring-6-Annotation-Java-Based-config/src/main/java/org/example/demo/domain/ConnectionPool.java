package org.example.demo.domain;

import lombok.AllArgsConstructor;
import lombok.ToString;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@ToString
public class ConnectionPool {

    private String username;
    private String password;
    private int poolSize;
    private String url;

//    private List<Object> args;
//    private Map<String,Object> properties;
}
