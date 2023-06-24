package com.healthtrack.calculator.service.login;

import com.healthtrack.calculator.domain.LogInUser;
import com.healthtrack.calculator.domain.ResponseBody;
import jakarta.annotation.Resource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service("logInService")
public class LogInServiceProvider implements LogInService{


    @Resource
    private AuthenticationManager authenticationManager;

    @Override
    public ResponseBody<LogInUser> login(LogInUser user) {

        // authentication
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(token);

        return null;
    }
}
