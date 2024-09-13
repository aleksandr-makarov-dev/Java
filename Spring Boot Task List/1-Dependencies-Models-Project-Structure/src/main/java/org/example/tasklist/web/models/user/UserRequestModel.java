package org.example.tasklist.web.models.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.example.tasklist.web.models.validation.OnCreate;
import org.example.tasklist.web.models.validation.OnUpdate;
import org.hibernate.validator.constraints.Length;

@Data
public class UserRequestModel {
    @NotNull(message = "Id must be not null", groups = OnUpdate.class)
    private Long id;

    @NotNull(message = "name must be not null", groups = {OnCreate.class, OnUpdate.class})
    @Length(max = 255, message = "name must be no longer than 255 characters", groups = {OnCreate.class, OnUpdate.class})
    private String name;

    @NotNull(message = "username must be not null", groups = {OnCreate.class, OnUpdate.class})
    @Length(max = 255, message = "username must be no longer than 255 characters", groups = {OnCreate.class, OnUpdate.class})
    private String username;

    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    @NotNull(message = "password must be not null", groups = {OnCreate.class, OnUpdate.class})
    private String password;

    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    @NotNull(message = "password confirmation must be not null", groups = {OnUpdate.class})
    private String passwordConfirmation;
}
