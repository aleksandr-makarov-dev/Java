package org.example.tasklist.repository;

import org.example.tasklist.domain.user.Role;
import org.example.tasklist.domain.user.User;

import java.util.Optional;

public interface UserRepository {
    Optional<User> findById(Long id);

    Optional<User> findByUsername(String username);

    void create(User user);

    void update(User user);

    void delete(Long id);

    void setUserRole(Long userId, Role role);

    boolean isOwner(Long userId, Long taskId);


}
