package com.healthtrack.calculator.filter;

import com.healthtrack.calculator.pojo.UserCredential;
import com.healthtrack.calculator.service.userCredential.UserCredentialService;
import com.healthtrack.calculator.utils.JwtUtil;
import jakarta.annotation.Resource;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;

@Component
@PropertySource("classpath:filter/jwtSecurityFilter.properties")
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Value("${REQUEST_HEADER}")
    private String requestHeader;

    @Resource
    private UserCredentialService userCredentialService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // get token
        String token = request.getHeader(requestHeader);

        // if header has token and token is valid
        if(StringUtils.hasText(token) && JwtUtil.validateToken(token)){
            String username = JwtUtil.getUsernameFromToken(token);
            UserCredential userCredentialByUsername = userCredentialService.getUserCredentialByUsername(username);

            // if the user does not exist
            if(Objects.isNull(userCredentialByUsername)){
                throw new UsernameNotFoundException("Username is not found");
            }

            // inject into authentication context holder
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(userCredentialByUsername.getUsername(),
                            userCredentialByUsername.getPassword(), null);
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            filterChain.doFilter(request, response);
            return;
        }

        filterChain.doFilter(request, response);

    }

}
