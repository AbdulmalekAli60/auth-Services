package com.example.signup_login.Service;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.function.Function;

@Service
public interface JWTService {

     String generateJWTToken(String userName);

     <T> T extractClaim(String token, Function<Claims, T> claimResolver);

     String extractUserName(String token);

     Boolean validateToken(String token, UserDetails userDetails);

     Claims extractAllClaims(String token);

     Boolean isTokenExpired(String token);

     Date extractExpiration(String token);

}
