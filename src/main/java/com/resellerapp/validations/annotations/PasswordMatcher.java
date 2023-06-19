package com.resellerapp.validations.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.resellerapp.validations.validators.PasswordMatcherValidator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Constraint(validatedBy = PasswordMatcherValidator.class)
public @interface PasswordMatcher {
    
    String message() default "Password miss match!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
