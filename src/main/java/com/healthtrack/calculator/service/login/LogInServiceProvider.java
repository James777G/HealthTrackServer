package com.healthtrack.calculator.service.login;

import com.healthtrack.calculator.domain.LogInUser;
import com.healthtrack.calculator.domain.ResponseBody;
import com.healthtrack.calculator.utils.JwtUtil;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service("logInService")
public class LogInServiceProvider implements LogInService{


    @Resource
    private AuthenticationManager authenticationManager;

    @Override
    public ResponseBody<LogInUser> login(LogInUser user) {

        // authentication
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword());

        try{
            Authentication authenticate = authenticationManager.authenticate(token);

            // create JWT
            UserDetails userDetails = (UserDetails) authenticate.getPrincipal();
            String username = userDetails.getUsername();
            String JWT = JwtUtil.generateToken(username);
            return new ResponseBody<>(true, "Login is successful", JWT, null);

        } catch(AuthenticationException e){

            return new ResponseBody<>(false, "Wrong User Credential", null, null);
        }




    }
}
