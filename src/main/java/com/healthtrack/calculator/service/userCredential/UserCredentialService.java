package com.healthtrack.calculator.service.userCredential;

import com.healthtrack.calculator.pojo.UserCredential;

import java.util.UUID;

public interface UserCredentialService {


    /**
     * Select user_credential based on id
     * @param uuid id
     * @return UserCredential Object
     */
    UserCredential getUserCredentialById(UUID uuid);


    /**
     * Select user_credential based on username
     * @param username unique username
     * @return UserCredential Object
     */
    UserCredential getUserCredentialByUsername(String username);


    /**
     * Insert into user_credential table
     * @param userCredential pojo
     */
    void insertUserCredential(UserCredential userCredential);


    /**
     * Delete user_credential based on username
     * @param username unique username
     */
    void deleteUserCredentialByUsername(String username);
}
