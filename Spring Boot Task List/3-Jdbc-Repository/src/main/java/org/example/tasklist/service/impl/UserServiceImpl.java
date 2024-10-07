package org.example.tasklist.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.tasklist.domain.exception.ResourceNotFoundException;
import org.example.tasklist.domain.user.Role;
import org.example.tasklist.domain.user.User;
import org.example.tasklist.repository.UserRepository;
import org.example.tasklist.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional(readOnly = true)
    public User getById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("user not found"));
    }

    @Override
    @Transactional(readOnly = true)
    public User getByUsername(String username) {

        return userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("user not found"));
    }

    @Override
    @Transactional
    public User create(User user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new IllegalArgumentException("username already exists");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.create(user);

        Set<Role> roles = Set.of(Role.ROLE_USER);
        userRepository.setUserRole(user.getId(), Role.ROLE_USER);
        user.setRoles(roles);

        return user;
    }

    @Override
    @Transactional
    public User update(Long id, User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.update(user);

        return user;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        userRepository.delete(id);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean isOwner(Long userId, Long taskId) {
        return userRepository.isOwner(userId, taskId);
    }
}
