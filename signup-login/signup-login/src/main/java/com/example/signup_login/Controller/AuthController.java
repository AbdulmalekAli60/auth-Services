package com.example.signup_login.Controller;

import com.example.signup_login.DTO.JWTDto;
import com.example.signup_login.DTO.SignInDTO;
import com.example.signup_login.DTO.UserDTO;
import com.example.signup_login.Service.AuthService;
import com.example.signup_login.Service.JWTService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;
    private final JWTService jwtService;


    public AuthController(AuthService service ,JWTService jwtService1){
        this.authService = service;
        this.jwtService = jwtService1;
    }

//    sign up a new user "register"
    @PostMapping("/signup")
    public ResponseEntity<JWTDto> registerNewUser(@RequestBody UserDTO userDTO){

      JWTDto jwtDto = authService.register(userDTO);

        return new ResponseEntity<>(jwtDto, HttpStatus.CREATED);
    }

    //sing in
    @PostMapping("/login")
    public ResponseEntity<JWTDto> signin(@RequestBody SignInDTO signInDTO) {
        JWTDto jwtDto = authService.Signin(signInDTO);
        return ResponseEntity.ok(jwtDto);
    }

    @GetMapping("/hi")
    public ResponseEntity<String> sayHi(){
        return ResponseEntity.ok("Hi");
    }


}
