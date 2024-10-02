package org.example.demo.integration.service;

import lombok.RequiredArgsConstructor;
import org.example.demo.annotation.IntegrationTest;
import org.example.demo.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.TestConstructor;

@IntegrationTest
@RequiredArgsConstructor
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
public class UserServiceIT {

    private final UserService userService;

    @Test
    void test(){

    }
}
