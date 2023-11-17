package com.healthtrack.calculator.service.login;

import com.healthtrack.calculator.domain.ResponseBody;
import com.healthtrack.calculator.domain.SignUpRequest;
import com.healthtrack.calculator.exception.SystemException;
import com.healthtrack.calculator.pojo.UserCredential;
import com.healthtrack.calculator.service.email.VerificationCodeService;
import com.healthtrack.calculator.service.userCredential.UserCredentialService;
import com.healthtrack.calculator.utils.JwtUtil;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionException;

import java.util.UUID;

@Service("signUpService")
@Slf4j
public class SignUpServiceImpl implements SignUpService{

    @Resource
    private VerificationCodeService verificationCodeService;

    @Resource
    private UserCredentialService userCredentialService;

    @Override
    public ResponseBody<SignUpRequest> signUp(SignUpRequest request) throws SystemException {
        if(userCredentialService.getUserCredentialByUsername(request.getUsername()) == null){
            try{
                verificationCodeService.sendVerificationCode(convertToUserCredential(request), verificationCodeService.generateCode());
            }catch(SystemException e){
                return new ResponseBody<>(false, "Email Address Is Not Valid", null, 304, null);
            }
            return new ResponseBody<>(true, "Signup is successful", null, 200, null);
        }
        return new ResponseBody<>(false, "Username already exists", null, 305, null);
    }

    @Override
    public ResponseBody<SignUpRequest> verify(SignUpRequest request) {
        if(verificationCodeService.verifyCode(request.getUsername(), request.getVerificationCode())) {
            UserCredential user = convertToUserCredential(request);
            user.setId(UUID.randomUUID());
            try{
                userCredentialService.insertUserCredential(user);
            }catch(Exception e){
                log.warn("Database Error - Duplicates Detected");
                // Duplicates
                return new ResponseBody<>(false, "Database Error - Duplicates", null, 305, null);
            }
            // Success
            return new ResponseBody<>(true, "Verification is successful", JwtUtil.generateToken(request.getUsername()), 200, null);
        }
        // Verification Code Wrong
        return new ResponseBody<>(false, "Verification failed", null, 401, null);
    }

    private UserCredential convertToUserCredential(SignUpRequest request){
        UserCredential userCredential = new UserCredential();
        userCredential.setUsername(request.getUsername());
        userCredential.setPassword(request.getPassword());
        userCredential.setEmail(request.getEmail());
        return userCredential;
    }


}
