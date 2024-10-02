package org.example.demo.annotation;

import org.example.demo.Application;
import org.example.demo.integration.TestApplicationRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestConstructor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@ActiveProfiles("test")
@SpringBootTest(classes = {Application.class, TestApplicationRunner.class})
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
public @interface IntegrationTest {
}
