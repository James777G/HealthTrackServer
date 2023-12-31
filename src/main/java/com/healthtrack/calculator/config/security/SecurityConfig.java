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
import org.springframework.web.cors.CorsConfiguration;

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
    @SuppressWarnings("all")
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http
                .cors().configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues()).and() // Add this to integrate with WebMvcConfigurer CORS settings
                .csrf().disable() // Disable CSRF protection as per your setup
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/signup").permitAll() // Permit all access to /signup
                        .requestMatchers("/verify").permitAll()
                        .requestMatchers("/login").anonymous() // Only anonymous users can access login
                        .anyRequest().authenticated()) // All other requests should be authenticated
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class) // Add your JWT filter
                .addFilterAfter(authenticatedLoginFilter, JwtAuthenticationFilter.class); // Add your authenticated login filter

        return http.build();
    }
}
