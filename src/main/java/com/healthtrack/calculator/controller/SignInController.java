package com.healthtrack.calculator.controller;

import com.healthtrack.calculator.domain.ResponseBody;
import com.healthtrack.calculator.domain.LogInUser;
import com.healthtrack.calculator.service.login.LogInService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class SignInController {

    @Resource
    private LogInService logInService;

    @PostMapping("/login")
    public ResponseBody<LogInUser> logIn(@RequestBody LogInUser user){
        log.info("Processing Request1");
        return logInService.login(user);
    }

    @PostMapping("/test")
    public String test(){
        log.info("Processing Request2");
        return "test";
    }

    @GetMapping("/test")
    public String test2(){
        return "test2";
    }
}
