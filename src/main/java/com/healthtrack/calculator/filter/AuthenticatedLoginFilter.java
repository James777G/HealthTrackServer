package com.healthtrack.calculator.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;

@Component
public class AuthenticatedLoginFilter extends OncePerRequestFilter {


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // get request endpoint
        String requestURI = request.getRequestURI();

        // request to /login while the user has already login
        if(StringUtils.pathEquals(requestURI, "/login") && isAuthenticated()){
            response.sendRedirect("/test");
            return;
        }

        filterChain.doFilter(request, response);
    }


    /**
     * Check is the user if authenticated
     * @return true/false
     */
    private boolean isAuthenticated(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(Objects.isNull(authentication)){
            return false;
        }
        return authentication.isAuthenticated();
    }
}
