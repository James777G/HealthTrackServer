package com.healthtrack.calculator.service.login;

import com.healthtrack.calculator.domain.ResponseBody;
import com.healthtrack.calculator.domain.SignUpRequest;
import com.healthtrack.calculator.exception.SystemException;
import com.healthtrack.calculator.pojo.UserCredential;
import com.healthtrack.calculator.service.email.VerificationCodeService;
import com.healthtrack.calculator.service.userCredential.UserCredentialService;
import com.healthtrack.calculator.utils.JwtUtil;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service("signUpService")
public class SignUpServiceImpl implements SignUpService{

    @Resource
    private VerificationCodeService verificationCodeService;

    @Resource
    private UserCredentialService userCredentialService;

    @Override
    public ResponseBody<SignUpRequest> signUp(SignUpRequest request) throws SystemException {
        if(userCredentialService.getUserCredentialByUsername(request.getUsername()) == null){
            verificationCodeService.sendVerificationCode(convertToUserCredential(request), verificationCodeService.generateCode());
            return new ResponseBody<>(true, "Signup is successful", null, null);
        }
        return new ResponseBody<>(false, "Username already exists", null, null);
    }

    @Override
    public ResponseBody<SignUpRequest> verify(SignUpRequest request) {
        if(verificationCodeService.verifyCode(request.getUsername(), request.getVerificationCode())) {
            UserCredential user = convertToUserCredential(request);
            user.setId(UUID.randomUUID());
            userCredentialService.insertUserCredential(user);
            return new ResponseBody<>(true, "Verification is successful", JwtUtil.generateToken(request.getUsername()), null);
        }
        return new ResponseBody<>(false, "Verification failed", null, null);
    }

    private UserCredential convertToUserCredential(SignUpRequest request){
        UserCredential userCredential = new UserCredential();
        userCredential.setUsername(request.getUsername());
        userCredential.setPassword(request.getPassword());
        userCredential.setEmail(request.getEmail());
        return userCredential;
    }


}
