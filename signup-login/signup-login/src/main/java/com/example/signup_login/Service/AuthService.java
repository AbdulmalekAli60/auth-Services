package com.example.signup_login.Service;
import com.example.signup_login.DTO.JWTDto;
import com.example.signup_login.DTO.SignInDTO;
import com.example.signup_login.DTO.UserDTO;
public interface AuthService {

    public JWTDto register(UserDTO userDto);

    public JWTDto Signin(SignInDTO signInDTO);
}
