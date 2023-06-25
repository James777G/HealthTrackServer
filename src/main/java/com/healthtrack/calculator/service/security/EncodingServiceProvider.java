package com.healthtrack.calculator.service.security;

import jakarta.annotation.Resource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service("encodingService")
public class EncodingServiceProvider implements EncodingService{

    @Resource
    private PasswordEncoder passwordEncoder;

    @Override
    public String encode(String rawData) {
        return passwordEncoder.encode(rawData);
    }

    @Override
    public boolean match(String rawData, String encodedData) {
        return passwordEncoder.matches(rawData, encodedData);
    }
}
