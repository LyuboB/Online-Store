package com.resellerapp.dtos.auth;

import com.resellerapp.validations.annotations.LoginDataValidation;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@LoginDataValidation
public class UserLoginDTO {

    @NotNull
    @Email
    private String email;

    @NotNull
    private String password;
}
