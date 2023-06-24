package com.healthtrack.calculator.service.userCredential;

import com.healthtrack.calculator.pojo.UserCredential;
import jakarta.annotation.Resource;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Objects;


@Service("userDetailsService")
public class UserDetailsServiceProvider implements UserDetailsService {

    @Resource
    private UserCredentialService userCredentialService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserCredential userCredential = userCredentialService.getUserCredentialByUsername(username);

        if(Objects.isNull(userCredential)){
            throw new UsernameNotFoundException("Username does not exist");
        }

        return new User(userCredential.getUsername(), userCredential.getPassword(), new ArrayList<>());
    }
}
