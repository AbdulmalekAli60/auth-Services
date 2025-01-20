package com.example.signup_login.Service.Impl;
import com.example.signup_login.DTO.JWTDto;
import com.example.signup_login.DTO.SignInDTO;
import com.example.signup_login.DTO.UserDTO;
import com.example.signup_login.Ecxeption.UserNotFoundException;
import com.example.signup_login.Ecxeption.WrongCredentialsException;
import com.example.signup_login.Repository.UserRepository;
import com.example.signup_login.Service.AuthService;
import com.example.signup_login.Entity.UserEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12);
    private final JWTServiceImpl JWTService;

    @Autowired
    AuthenticationManager authenticationManager;


    public AuthServiceImpl(ModelMapper mapper, UserRepository repo, JWTServiceImpl JwtService){
        this.modelMapper = mapper;
        this.userRepository = repo;
        this.JWTService = JwtService;
    }

    @Override
    public JWTDto register(UserDTO userDto) {

        validateRegistrationInput(userDto);

        // Check if username already exists
        if (userRepository.findByUserName(userDto.getUserName()) != null) {
            throw new WrongCredentialsException("Username already exists");
        }

        // Check if email already exists
        if (userRepository.existsByEmail(userDto.getEmail())) {
            throw new WrongCredentialsException("Email already registered");
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

    @Override
    public JWTDto Signin(SignInDTO signInDTO) {
        try {
            // Get user from database for debugging
            UserEntity user = userRepository.findByUserName(signInDTO.getUserName());
            if (user != null) {
                System.out.println("User found in database");
                System.out.println("Stored encoded password: " + user.getPassword());
            } else {
                throw new UserNotFoundException("User not found");
            }

            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            signInDTO.getUserName(),
                            signInDTO.getPassword()
                    )
            );

            System.out.println("Authentication result: " + authentication.isAuthenticated());

            if (authentication.isAuthenticated()) {
                String token = JWTService.generateJWTToken(signInDTO.getUserName());
                return new JWTDto(token,signInDTO);
            }
        } catch (AuthenticationException e) {
            System.out.println("Authentication failed with error: " + e.getMessage());
            e.printStackTrace();
            throw new WrongCredentialsException("Invalid username or password");
        }

        throw new WrongCredentialsException("Authentication failed");
    }

    private void validateRegistrationInput(UserDTO userDto) {
        if (userDto.getUserName() == null || userDto.getUserName().trim().isEmpty()) {
            throw new WrongCredentialsException("Username is required");
        }

        if (userDto.getPassword() == null || userDto.getPassword().trim().isEmpty()) {
            throw new WrongCredentialsException("Password is required");
        }

        if (userDto.getEmail() == null || userDto.getEmail().trim().isEmpty()) {
            throw new WrongCredentialsException("Email is required");
        }

        // Add password strength validation
        if (!isPasswordStrong(userDto.getPassword())) {
            throw new WrongCredentialsException("Password must be at least 8 characters long and contain at least one uppercase letter, one lowercase letter, and one number");
        }

        // Add email format validation
        if (!isValidEmail(userDto.getEmail())) {
            throw new WrongCredentialsException("Invalid email format");
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
