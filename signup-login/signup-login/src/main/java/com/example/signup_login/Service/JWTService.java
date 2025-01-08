package com.example.signup_login.Service;

import org.springframework.stereotype.Service;

@Service
public interface JWTService {

    String generateJWTToken(String userName);
}
