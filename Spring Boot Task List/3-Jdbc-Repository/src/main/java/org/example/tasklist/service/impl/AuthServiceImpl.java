package org.example.tasklist.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.tasklist.domain.user.User;
import org.example.tasklist.service.AuthService;
import org.example.tasklist.service.UserService;
import org.example.tasklist.web.models.auth.JwtResponseModel;
import org.example.tasklist.web.models.auth.LoginRequestModel;
import org.example.tasklist.web.security.JwtProvider;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtProvider jwtProvider;

    @Override
    public JwtResponseModel login(LoginRequestModel credentials) {

        // throws exception if password is incorrect
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        credentials.getUsername(),
                        credentials.getPassword()
                ));

        User foundUser = userService.getByUsername(credentials.getUsername());
        var jwtResponse = JwtResponseModel
                .builder()
                .id(foundUser.getId())
                .username(foundUser.getUsername())
                .refreshToken(jwtProvider.createRefreshToken(foundUser.getId(), foundUser.getUsername()))
                .accessToken(jwtProvider.createAccessToken(foundUser.getId(), foundUser.getUsername(), foundUser.getRoles()))
                .build();

        return jwtResponse;
    }

    @Override
    public JwtResponseModel refresh(String refreshToken) {
        return jwtProvider.refreshUserTokens(refreshToken);
    }
}
