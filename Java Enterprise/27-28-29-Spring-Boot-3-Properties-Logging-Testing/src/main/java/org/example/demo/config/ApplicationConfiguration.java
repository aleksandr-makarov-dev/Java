package org.example.demo.config;

import org.example.demo.domain.ConnectionPool;
import org.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.*;

//@ImportResource("classpath:application.xml")
@Configuration
@ComponentScan("org.example.demo")
@PropertySource("classpath:application.yaml")
public class ApplicationConfiguration {

    @Bean
    public ConnectionPool connectionPool(){
        return  new ConnectionPool("postgres","root",12,"myurl");
    }

    @Bean
    @Scope(BeanDefinition.SCOPE_SINGLETON)
    public UserRepository userRepository(){
        return  new UserRepository(connectionPool());
    }

    @Bean
//    @Profile("production")
    public ConnectionPool connectionPool2(@Value("${db.username}") String username){
        return  new ConnectionPool(username,"super-secret-password",100,"url");
    }
}
