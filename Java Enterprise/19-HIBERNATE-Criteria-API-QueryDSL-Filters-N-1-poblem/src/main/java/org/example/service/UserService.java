package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.dao.UserRepository;
import org.example.dto.UserCreateDto;
import org.example.dto.UserGetDto;
import org.example.mapper.UserCreateMapper;
import org.example.mapper.UserGetMapper;

import java.util.Optional;

@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserGetMapper userGetMapper;
    private final UserCreateMapper userCreateMapper;

    public boolean delete(Integer id){
        var userOptional = userRepository.findById(id);
        userOptional.ifPresent(user -> userRepository.delete(id));

        return userOptional.isPresent();
    }

    public Optional<UserGetDto> findById(Integer id){
        return userRepository.findById(id).map(userGetMapper::mapFrom);
    }

    public Integer create(UserCreateDto userDto){
        var userEntity = userCreateMapper.mapFrom(userDto);
        return userRepository.save(userEntity).getId();
    }
}
