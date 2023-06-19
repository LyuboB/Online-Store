package com.resellerapp.services.auth;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.resellerapp.domain.entities.User;
import com.resellerapp.domain.helper.LoggedUser;
import com.resellerapp.dtos.auth.UserLoginDTO;
import com.resellerapp.dtos.auth.UserRegisterDTO;
import com.resellerapp.repository.UserRepository;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final LoggedUser loggedUser;
    private final ModelMapper modelMapper;


    public AuthServiceImpl(UserRepository userRepository, LoggedUser loggedUser, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.loggedUser = loggedUser;
        this.modelMapper = modelMapper;
    }

    @Override
    public void registerUser(UserRegisterDTO userRegisterDTO) {

        User userToSave = this.modelMapper.map(userRegisterDTO, User.class);

        this.userRepository.saveAndFlush(userToSave);
    }

    @Override
    public void loginUser(UserLoginDTO userLoginDTO) {

        User user = this.userRepository.findUserByEmail(userLoginDTO.getEmail()).get();

        this.loggedUser.setId(user.getId());
    }

    @Override
    public void logoutUser() {
        loggedUser.clearUser();
    }
    
}
