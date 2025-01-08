package com.example.signup_login.Controller;

import com.example.signup_login.DTO.JWTDto;
import com.example.signup_login.DTO.UserDTO;
import com.example.signup_login.Service.SignupService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/auth")

public class SignupController {

    private final SignupService signupService;

    public  SignupController(SignupService service){
        this.signupService = service;
    }

//    sign up a new user "register"
    @PostMapping("/signup")
    public ResponseEntity<JWTDto> registerNewUser(@RequestBody UserDTO userDTO){

      JWTDto jwtDto = signupService.register(userDTO);

        return new ResponseEntity<>(jwtDto, HttpStatus.CREATED);
    }


}
