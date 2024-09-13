package org.example.tasklist.web.models.auth;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LoginRequestModel {

    @NotNull(message = "username must be not null")
    private String username;

    @NotNull(message = "password must be not null")
    private String password;
}
