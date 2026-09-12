package com.vasileva.calorizer.model.auth;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {

    @NotBlank(message = "validation.login.empty")
    private String name;

    @NotBlank(message = "validation.password.empty")
    private String password;
}
