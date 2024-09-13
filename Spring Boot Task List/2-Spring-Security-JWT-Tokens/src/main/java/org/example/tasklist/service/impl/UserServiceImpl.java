package org.example.tasklist.service.impl;

import org.example.tasklist.domain.user.User;
import org.example.tasklist.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public User getById(Long id) {
        return null;
    }

    @Override
    public User getByUsername(String username) {
        return null;
    }

    @Override
    public User create(User user) {
        return null;
    }

    @Override
    public User update(Long id, User user) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public boolean isOwner(Long userId, Long taskId) {
        return false;
    }
}
