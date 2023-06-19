package com.resellerapp.services.auth;

import com.resellerapp.dtos.auth.UserLoginDTO;
import com.resellerapp.dtos.auth.UserRegisterDTO;

public interface AuthService {
    
    void registerUser(UserRegisterDTO userRegisterDTO);

    void loginUser(UserLoginDTO userLoginDTO);

    void logoutUser();

}
