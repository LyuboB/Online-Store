package com.resellerapp.validations.validators;

import org.springframework.beans.factory.annotation.Autowired;

import com.resellerapp.dtos.auth.UserLoginDTO;
import com.resellerapp.dtos.user.UserDTO;
import com.resellerapp.services.user.UserServiceImpl;
import com.resellerapp.validations.annotations.LoginDataValidation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class LoginFormValidator implements ConstraintValidator<LoginDataValidation, UserLoginDTO> {

    private final UserServiceImpl userServiceImpl;

    @Autowired
    public LoginFormValidator(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @Override
    public void initialize(LoginDataValidation constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(UserLoginDTO userLoginDTO, ConstraintValidatorContext context) {

        UserDTO userDTO = this.userServiceImpl.findUserByEmail(userLoginDTO.getEmail());
        
        return userDTO.getId() != null && userDTO.getPassword().equals(userLoginDTO.getPassword());
    }
}