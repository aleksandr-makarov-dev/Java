package org.example.tasklist.web.controller;

import lombok.RequiredArgsConstructor;
import org.example.tasklist.domain.user.User;
import org.example.tasklist.service.AuthService;
import org.example.tasklist.service.UserService;
import org.example.tasklist.web.mappers.UserMapper;
import org.example.tasklist.web.models.auth.JwtResponseModel;
import org.example.tasklist.web.models.auth.LoginRequestModel;
import org.example.tasklist.web.models.user.UserRequestModel;
import org.example.tasklist.web.models.user.UserResponseModel;
import org.example.tasklist.web.models.validation.OnCreate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Validated
public class AuthController {
    private final AuthService authService;
    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping("login")
    public JwtResponseModel login(@Validated @RequestBody LoginRequestModel loginRequest) {
        return authService.login(loginRequest);
    }

    @PostMapping("register")
    public UserResponseModel register(@Validated(OnCreate.class) @RequestBody UserRequestModel userRequest){
        User userToCreate = userMapper.toEntity(userRequest);
        User createdUser = userService.create(userToCreate);

        return userMapper.toResponseModel(createdUser);
    }

    @PostMapping("refresh")
    public JwtResponseModel refresh(@RequestBody String refreshToken) {
        return authService.refresh(refreshToken);
    }
}
