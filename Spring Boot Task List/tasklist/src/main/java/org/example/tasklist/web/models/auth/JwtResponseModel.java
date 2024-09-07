package org.example.tasklist.web.models.auth;

import lombok.Data;

@Data
public class JwtResponseModel {
    private Long id;
    private String username;
    private String accessToken;
    private String refreshToken;
}
