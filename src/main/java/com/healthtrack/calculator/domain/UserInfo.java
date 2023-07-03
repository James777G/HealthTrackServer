package com.healthtrack.calculator.domain;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;

@Data
public class UserInfo implements Serializable {
    @Serial
    private static final long serialVersionUID = 55736572496666L;

    private int userId;
    private String userName;
    private int age;
    private int sex;
    private int height;
    private int weight;
    private HashSet<String> symptoms;



}

