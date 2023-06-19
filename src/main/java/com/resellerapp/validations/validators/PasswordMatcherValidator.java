package com.resellerapp.validations.validators;

import com.resellerapp.dtos.auth.UserRegisterDTO;
import com.resellerapp.validations.annotations.PasswordMatcher;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordMatcherValidator implements ConstraintValidator<PasswordMatcher, UserRegisterDTO> {

    @Override
    public void initialize(PasswordMatcher constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(UserRegisterDTO userRegisterDTO, ConstraintValidatorContext context) {

        if (userRegisterDTO.getPassword() != null
                && userRegisterDTO.getPassword().equals(userRegisterDTO.getConfirmPassword())) {
            return true;
        }

        context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate())
                .addPropertyNode(userRegisterDTO.getConfirmPassword())
                .addConstraintViolation()
                .disableDefaultConstraintViolation();
        
        return false;
    }
    
}
