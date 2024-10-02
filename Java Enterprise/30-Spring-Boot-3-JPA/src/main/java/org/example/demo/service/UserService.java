package org.example.demo.service;

import lombok.AllArgsConstructor;
import lombok.ToString;
import org.example.demo.mapper.UserMapper;
import org.example.demo.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@ToString
public class UserService {
    private final UserMapper userMapper;
    private final UserRepository userRepository;
}
