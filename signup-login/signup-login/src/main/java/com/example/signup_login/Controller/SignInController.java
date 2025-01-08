package com.example.signup_login.Controller;

import com.example.signup_login.DTO.JWTDto;
import com.example.signup_login.DTO.SignInDTO;
import com.example.signup_login.Service.SignInService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/auth")
public class SignInController {

    private final SignInService service;

    public SignInController(SignInService service) {
        this.service = service;
    }

    //sing in
    @PostMapping("/login")
    public ResponseEntity<JWTDto> signin(@RequestBody SignInDTO signInDTO) {
        JWTDto jwtDto = service.Signin(signInDTO);
        return ResponseEntity.ok(jwtDto);
    }
}
