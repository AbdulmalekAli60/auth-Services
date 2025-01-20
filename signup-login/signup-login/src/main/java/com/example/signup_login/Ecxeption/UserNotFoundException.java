package com.example.signup_login.Ecxeption;

public class UserNotFoundException extends RuntimeException{


    public UserNotFoundException(String message) {
        super(message);
    }
}
