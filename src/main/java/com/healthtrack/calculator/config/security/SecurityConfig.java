package com.healthtrack.calculator.config.security;

import com.healthtrack.calculator.filter.AuthenticatedLoginFilter;
import com.healthtrack.calculator.filter.JwtAuthenticationFilter;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Collections;


/**
 * Setting up security filter chain:
 * <p>
 *     1. Allow any requests to the "/login" URL, regardless of the user's authentication status.
 *     2. Allow any requests already authenticated
 *     3. Unauthenticated requests must authenticate first
 * </p>
 */
@Configuration
public class SecurityConfig {


    @Resource
    private UserDetailsService userDetailsService;

    @Resource
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Resource
    private AuthenticatedLoginFilter authenticatedLoginFilter;


    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }



    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager() {
        return new ProviderManager(Collections.singletonList(authenticationProvider()));
    }


    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((requests) -> requests
                .requestMatchers("/login").anonymous()
                .anyRequest()
                .authenticated());

        // register
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        http.addFilterAfter(authenticatedLoginFilter, JwtAuthenticationFilter.class);
        return http.build();

    }
}
