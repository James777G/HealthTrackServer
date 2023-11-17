package com.healthtrack.calculator.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SignUpRequest {

    @NotNull(message = "The username must not be null")
    @NotBlank(message = "The username must not be blank")
    private String username;

    @NotNull(message = "The password must not be null")
    @NotBlank(message = "The password must not be blank")
    private String password;

    @NotNull(message = "The email must not be null")
    @NotBlank(message = "The email must not be blank")
    private String email;

    private String verificationCode;
}
