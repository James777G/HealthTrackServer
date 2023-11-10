package com.healthtrack.calculator.domain;

import lombok.Data;

@Data
public class SignUpRequest {

    private String username;
    private String password;
    private String email;
    private String verificationCode;
}
