package com.example.signup_login.DTO;


import jakarta.validation.constraints.NotBlank;

public class SignInDTO {

    @NotBlank(message = "User name is required")
    private String userName;
    @NotBlank(message = "Password is required")
    private String password;

    public SignInDTO(String password, String userName) {
        this.password = password;
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String pass) {
        password = pass;
    }
}
