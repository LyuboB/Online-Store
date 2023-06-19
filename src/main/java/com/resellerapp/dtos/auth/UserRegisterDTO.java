package com.resellerapp.dtos.auth;

import com.resellerapp.validations.annotations.PasswordMatcher;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@PasswordMatcher
public class UserRegisterDTO {

    @NotNull
    @Size(min = 3, max = 20)
    private String name;

    @NotNull
    @Email
    private String email;

    @NotNull
    @Size(min = 3, max = 20)
    private String password;

    @NotNull
    @Size(min = 3, max = 20)
    private String confirmPassword;
}
