package org.example.tasklist.service;

import org.example.tasklist.web.models.auth.JwtResponseModel;
import org.example.tasklist.web.models.auth.LoginRequestModel;

public interface AuthService {
    JwtResponseModel login(LoginRequestModel credentials);
    JwtResponseModel refresh(String refreshToken);
}
