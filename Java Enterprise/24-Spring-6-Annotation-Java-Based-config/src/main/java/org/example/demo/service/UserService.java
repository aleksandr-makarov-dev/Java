package org.example.demo.service;

import lombok.AllArgsConstructor;
import lombok.ToString;
import org.example.demo.mapper.UserMapper;
import org.example.demo.repository.UserRepository;

@AllArgsConstructor
@ToString
public class UserService {
    private final UserMapper userMapper;
    private final UserRepository userRepository;
}
