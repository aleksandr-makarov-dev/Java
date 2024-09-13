package org.example.tasklist.service;

import org.example.tasklist.domain.user.User;

public interface UserService {

    User getById(Long id);

    User getByUsername(String username);

    User create(User user);

    User update(Long id, User user);

    void delete(Long id);

    boolean isOwner(Long userId, Long taskId);
}
