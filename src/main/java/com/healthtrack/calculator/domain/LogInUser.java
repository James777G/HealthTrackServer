package com.healthtrack.calculator.domain;

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

    private String username;
    private String password;
}
