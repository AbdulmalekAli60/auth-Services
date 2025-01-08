package com.example.signup_login.Service;
import com.example.signup_login.DTO.JWTDto;
import com.example.signup_login.DTO.UserDTO;
public interface SignupService {

    public JWTDto register(UserDTO userDto);
}
