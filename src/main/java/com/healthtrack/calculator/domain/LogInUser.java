package com.healthtrack.calculator.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Request format from frontend
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LogInUser implements Serializable {

    @NotNull(message = "The username must not be null")
    @NotBlank(message = "The username must not be blank")
    private String username;

    @NotNull(message = "The password must not be null")
    @NotBlank(message = "The password must not be blank")
    private String password;
}
