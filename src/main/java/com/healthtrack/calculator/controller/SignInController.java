package com.healthtrack.calculator.controller;

import com.healthtrack.calculator.domain.ResponseBody;
import com.healthtrack.calculator.domain.LogInUser;
import com.healthtrack.calculator.domain.SignUpRequest;
import com.healthtrack.calculator.exception.SystemException;
import com.healthtrack.calculator.service.login.LogInService;
import com.healthtrack.calculator.service.login.SignUpService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class SignInController {

    @Resource
    private LogInService logInService;

    @Resource
    private SignUpService signUpService;

    @PostMapping("/login")
    public ResponseBody<LogInUser> logIn(@Validated @RequestBody LogInUser user){
        log.info("Processing Log In Request For " + user.getUsername());
        return logInService.login(user);
    }

    @PostMapping("/signup")
    public ResponseBody<SignUpRequest> signUp(@Validated @RequestBody SignUpRequest request) throws SystemException {
        log.info("Processing Sign Up Request For " + request.getUsername());
        return signUpService.signUp(request);
    }

    @PostMapping("/verify")
    public ResponseBody<SignUpRequest> verify(@Validated @RequestBody SignUpRequest request) throws SystemException {
        log.info("Processing Verify Request For " + request.getUsername());
        return signUpService.verify(request);
    }

    @GetMapping("/test")
    public String test(){
        return "123";
    }

}
