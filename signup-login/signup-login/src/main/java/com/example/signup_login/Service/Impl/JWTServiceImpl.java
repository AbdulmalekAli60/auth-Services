package com.example.signup_login.Service.Impl;

import com.example.signup_login.Service.JWTService;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Jwts;

import java.security.Key;

import java.util.Date;


@Service
public class JWTServiceImpl implements JWTService {

    @Value("${app.jwt-secret}")
    private String jwtSecrete;

    @Value("${app.jwt-expiration-milliseconds}")
    private long jwtExpiration;

    @Override
    public String generateJWTToken(String userName) {

        Date currentDate = new Date();
        Date expireDate = new Date(currentDate.getTime() + jwtExpiration);

        return Jwts.builder()
                .setSubject(userName)
                .setIssuedAt(currentDate)
                .setExpiration(expireDate)
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtSecrete);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
