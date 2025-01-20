package com.example.signup_login.Filter;

import com.example.signup_login.Ecxeption.ErrorResponse;
import com.example.signup_login.Ecxeption.UserNotFoundException;
import com.example.signup_login.Ecxeption.WrongCredentialsException;

import com.example.signup_login.Service.Impl.CustomerUserDetailsServiceImpl;
import com.example.signup_login.Service.JWTService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
// 1- get auth header 2- get the header that starts with Bearer
import java.io.IOException;
// having request if you need to do something to the request, and response if you want to add something to the response

@Component
public class JwtFilter extends OncePerRequestFilter { // for every request we want this filter to be activated
    // and in the filter chain you want this filter to be executed only once

    @Autowired
    private JWTService jwtService;

    @Autowired
    ApplicationContext applicationContext;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        try {
            String authHeader = request.getHeader("Authorization");
            String token = null;
            String username = null;

            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                token = authHeader.substring(7);
                username = jwtService.extractUserName(token);
            }

            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = applicationContext
                        .getBean(CustomerUserDetailsServiceImpl.class)
                        .loadUserByUsername(username);

                if (jwtService.validateToken(token, userDetails)) {
                    UsernamePasswordAuthenticationToken authToken =
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }
            filterChain.doFilter(request, response);
        } catch (UserNotFoundException | WrongCredentialsException e) {
            // Convert the exception to a response
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");

            ErrorResponse errorResponse = new ErrorResponse(
                    e.getMessage(),
                    "Authentication failed"
            );

            String jsonResponse = new ObjectMapper().writeValueAsString(errorResponse);
            response.getWriter().write(jsonResponse);
        }
    }}
