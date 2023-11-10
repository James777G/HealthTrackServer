package com.healthtrack.calculator.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Data
public class UserCredential implements Serializable {

    private UUID id;
    private String username;
    private String password;
    private String email;


}
