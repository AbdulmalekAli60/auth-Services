package com.example.signup_login.DTO;

public class JWTDto {
    private String accessToken;
    private final String tokenType = "Bearer";
    private Object userInfo;

    public JWTDto(String accessToken, Object userDetails) {
        this.accessToken = accessToken;
        this.userInfo = userDetails;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public Object getUserInfo() {
        return userInfo;
    }
}