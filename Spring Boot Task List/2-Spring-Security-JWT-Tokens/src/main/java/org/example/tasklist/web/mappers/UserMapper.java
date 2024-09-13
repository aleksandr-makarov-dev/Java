package org.example.tasklist.web.mappers;

import org.example.tasklist.domain.user.User;
import org.example.tasklist.web.models.user.UserRequestModel;
import org.example.tasklist.web.models.user.UserResponseModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserRequestModel toRequestModel(User user);

    UserResponseModel toResponseModel(User user);

    User toEntity(UserRequestModel model);
}
