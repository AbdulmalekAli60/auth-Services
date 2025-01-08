package com.example.signup_login.Service;

import com.example.signup_login.DTO.JWTDto;
import com.example.signup_login.DTO.SignInDTO;

public interface SignInService {

    public JWTDto Signin(SignInDTO signInDTO);
}
