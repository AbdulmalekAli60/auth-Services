package com.example.signup_login.Ecxeption;

public class WrongCredentialsException extends RuntimeException {

    public WrongCredentialsException(String message){
        super(message);
    }

    public WrongCredentialsException(String message, Throwable cause) {
        super(message, cause);
    }
}
