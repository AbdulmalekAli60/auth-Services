package com.example.signup_login.Service.Impl;

import com.example.signup_login.DTO.JWTDto;
import com.example.signup_login.DTO.SignInDTO;
import com.example.signup_login.Ecxeption.UserNotFoundException;
import com.example.signup_login.Entity.UserEntity;
import com.example.signup_login.Repository.UserRepository;
import com.example.signup_login.Service.SignInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.signup_login.Ecxeption.WrongCredentialsException;


@Service
public class SignInServiceImpl implements SignInService {


    private final UserRepository userRepository;
    private final JWTServiceImpl JWTService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12);


    @Autowired
    AuthenticationManager authenticationManager;

    public SignInServiceImpl(UserRepository userRepository, JWTServiceImpl service) {
        this.userRepository = userRepository;
        this.JWTService = service;
    }

    @Override
    public JWTDto Signin(SignInDTO signInDTO) {
        try {
            // Debug logging
            System.out.println("Attempting login for user: " + signInDTO.getUserName());

            // Get user from database for debugging
            UserEntity user = userRepository.findByUserName(signInDTO.getUserName());
            if (user != null) {
                System.out.println("User found in database");
                System.out.println("Stored encoded password: " + user.getPassword());
            } else {
                System.out.println("User not found in database");
            }

            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            signInDTO.getUserName(),
                            signInDTO.getPassword()
                    )
            );

            System.out.println("Authentication result: " + authentication.isAuthenticated());

            if (authentication.isAuthenticated()) {
                String token = JWTService.generateJWTToken(signInDTO.getUserName());
                return new JWTDto(token,signInDTO);
            }
        } catch (AuthenticationException e) {
            System.out.println("Authentication failed with error: " + e.getMessage());
            e.printStackTrace();
            throw new WrongCredentialsException("Invalid username or password");
        }

        throw new WrongCredentialsException("Authentication failed");
    }
}
