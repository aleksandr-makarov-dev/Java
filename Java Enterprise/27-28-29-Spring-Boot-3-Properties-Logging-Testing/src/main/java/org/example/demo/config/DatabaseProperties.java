package org.example.demo.config;

import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;

@ConfigurationProperties(prefix = "db")
public record DatabaseProperties(
        String username,
        String password,
        String driver,
        String url,
        PoolProperties pool,
        List<PoolProperties> pools,
        Map<String, Object> properties
){
    public static  record PoolProperties(
            Integer size,
            Integer timeout
    ){}
}
