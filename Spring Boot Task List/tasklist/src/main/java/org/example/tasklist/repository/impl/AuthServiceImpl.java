package org.example.tasklist.repository.impl;

import org.example.tasklist.service.AuthService;
import org.example.tasklist.web.models.auth.JwtResponseModel;
import org.example.tasklist.web.models.auth.LoginRequestModel;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    @Override
    public JwtResponseModel login(LoginRequestModel credentials) {
        return null;
    }

    @Override
    public JwtResponseModel refresh(String refreshToken) {
        return null;
    }
}
