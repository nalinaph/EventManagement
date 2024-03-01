package com.eventmanagement.service.Impl;

import com.eventmanagement.entity.User;
import com.eventmanagement.payload.UserDto;
import com.eventmanagement.repository.UserRepository;
import com.eventmanagement.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {


    UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    ModelMapper modelMapper;

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = userRepository.save(mapToEntity(userDto));
        return mapToDto(user);

    }

    // Using ModelMapper to copy object for DTO to Entity and vice versa


    public User mapToEntity(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        return user;
    }

    public UserDto mapToDto(User user) {
        UserDto userDto = modelMapper.map(user, UserDto.class);
        return userDto;
    }
}
