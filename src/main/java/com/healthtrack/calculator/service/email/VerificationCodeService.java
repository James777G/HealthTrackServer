package com.healthtrack.calculator.service.email;

import com.healthtrack.calculator.exception.SystemException;
import com.healthtrack.calculator.pojo.UserCredential;

public interface VerificationCodeService {

    void sendVerificationCode(UserCredential user, String code) throws SystemException;

    boolean verifyCode(String username, String code);
    
    String generateCode();


}
