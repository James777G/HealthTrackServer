package com.healthtrack.calculator.domain;

import java.io.Serializable;
import java.util.HashSet;

public class UserInfo implements Serializable {
    private static final long serialVersionUID = 55736572496666L;

    private int userId;
    private String userName;
    private int age;
    private int sex;
    private int height;
    private int weight;
    private HashSet<String> symptoms;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public HashSet<String> getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(HashSet<String> symptoms) {
        this.symptoms = symptoms;
    }

}

