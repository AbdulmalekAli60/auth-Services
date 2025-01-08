package com.example.signup_login.Service.Impl;
import com.example.signup_login.DTO.JWTDto;
import com.example.signup_login.DTO.SignInDTO;
import com.example.signup_login.DTO.UserDTO;
import com.example.signup_login.Repository.UserRepository;
import com.example.signup_login.Service.SignupService;
import com.example.signup_login.Entity.UserEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SignupServiceImpl implements SignupService {

    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12);
    private final JWTServiceImpl JWTService;

    @Autowired
    AuthenticationManager authenticationManager;


    public SignupServiceImpl(ModelMapper mapper,UserRepository repo,JWTServiceImpl JwtService){
        this.modelMapper = mapper;
        this.userRepository = repo;
        this.JWTService = JwtService;
    }

    @Override
    public JWTDto register(UserDTO userDto) {

        validateRegistrationInput(userDto);

        // Check if username already exists
        if (userRepository.findByUserName(userDto.getUserName()) != null) {
            throw new RuntimeException("Username already exists");
        }

        // Check if email already exists
        if (userRepository.existsByEmail(userDto.getEmail())) {
            throw new RuntimeException("Email already registered");
        }

        UserEntity newUser = new UserEntity();
        newUser.setId(userDto.getId());
        newUser.setName(userDto.getName());
        newUser.setUserName(userDto.getUserName());
        newUser.setEmail(userDto.getEmail());
        newUser.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));

        // save user
        UserEntity savedUser =  userRepository.save(newUser);

        //generate token
        String token = JWTService.generateJWTToken(userDto.getUserName());

        return new JWTDto(token,savedUser);
    }

    private void validateRegistrationInput(UserDTO userDto) {
        if (userDto.getUserName() == null || userDto.getUserName().trim().isEmpty()) {
            throw new RuntimeException("Username is required");
        }

        if (userDto.getPassword() == null || userDto.getPassword().trim().isEmpty()) {
            throw new RuntimeException("Password is required");
        }

        if (userDto.getEmail() == null || userDto.getEmail().trim().isEmpty()) {
            throw new RuntimeException("Email is required");
        }

        // Add password strength validation
        if (!isPasswordStrong(userDto.getPassword())) {
            throw new RuntimeException("Password must be at least 8 characters long and contain at least one uppercase letter, one lowercase letter, and one number");
        }

        // Add email format validation
        if (!isValidEmail(userDto.getEmail())) {
            throw new RuntimeException("Invalid email format");
        }
    }

    private boolean isPasswordStrong(String password) {
        // Password must be at least 8 characters long
        // Must contain at least one uppercase letter
        // Must contain at least one lowercase letter
        // Must contain at least one number
        String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$";
        return password.matches(regex);
    }

    private boolean isValidEmail(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        return email.matches(regex);
    }
}
