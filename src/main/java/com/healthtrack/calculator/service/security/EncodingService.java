package com.healthtrack.calculator.service.security;

import jakarta.annotation.Resource;
import org.springframework.security.crypto.password.PasswordEncoder;

public interface EncodingService {

    String encode(String rawData);

    boolean match(String rawData);
}
