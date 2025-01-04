package com.example.signup_login.Service.Impl;

import com.example.signup_login.DTO.SignInDTO;
import com.example.signup_login.Ecxeption.UserNotFoundException;
import com.example.signup_login.Entity.UserEntity;
import com.example.signup_login.Repository.UserRepository;
import com.example.signup_login.Service.SignInService;
import org.springframework.stereotype.Service;
import com.example.signup_login.Ecxeption.WrongCredentialsException;


@Service
public class SignInServiceImpl implements SignInService {


    private final UserRepository userRepository;

    public SignInServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public String Signin(SignInDTO signInDTO) {

        if(signInDTO.getUserName() == null || signInDTO.getPassword() == null){
            throw new WrongCredentialsException("User name and password are required");
        }

        UserEntity user = userRepository.findByUserName(signInDTO.getUserName());


        if (user == null) {
            throw new UserNotFoundException("User was not found with this name");
        }

        // For debugging
        System.out.println("Stored password: " + user.getPassword());
        System.out.println("Provided password: " + signInDTO.getPassword());

        if (!user.getPassword().equals(signInDTO.getPassword())) {
            throw new WrongCredentialsException("The Password you entered is wrong");
        }

        if (!user.getUserName().equals(signInDTO.getUserName())) {
            throw new WrongCredentialsException("The user name you entered is wrong");
        }

        return "LogIn successful";
    }
}
