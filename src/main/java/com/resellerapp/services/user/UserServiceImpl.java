package com.resellerapp.services.user;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.resellerapp.domain.entities.User;
import com.resellerapp.dtos.user.UserDTO;
import com.resellerapp.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    public UserDTO findUserByEmail(String email) {
        return this.modelMapper.map(this.userRepository.findUserByEmail(email).orElse(new User()), UserDTO.class);
    }

    public UserDTO findById(Long id) {
        return this.modelMapper.map(this.userRepository.findById(id).orElse(new User()), UserDTO.class);
    }
}
