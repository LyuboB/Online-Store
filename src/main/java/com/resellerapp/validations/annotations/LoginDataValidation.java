package com.resellerapp.validations.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.resellerapp.validations.validators.LoginFormValidator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Constraint(validatedBy = LoginFormValidator.class)
public @interface LoginDataValidation {
    
    String message() default "Invalid user!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
