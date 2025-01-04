package com.example.signup_login.Service.Impl;
import com.example.signup_login.DTO.UserDTO;
import com.example.signup_login.Repository.UserRepository;
import com.example.signup_login.Service.SignupService;
import com.example.signup_login.Entity.UserEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class SignupServiceImpl implements SignupService {

    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    public SignupServiceImpl(ModelMapper mapper,UserRepository repo){
        this.modelMapper = mapper;
        this.userRepository = repo;
    }

    @Override
    public String register(UserDTO userDto) {

//        UserEntity newUser = modelMapper.map(userDto,UserEntity.class);

        // Ensure that the email field is not null
//        if (userDto.getEmail() == null) {
//            throw new IllegalArgumentException("Email cannot be null");
//        }


        UserEntity newUser = new UserEntity();
        newUser.setId(userDto.getId());
        newUser.setName(userDto.getName());
        newUser.setUserName(userDto.getName());
        newUser.setEmail(userDto.getEmail());
        newUser.setPassword(userDto.getPassword());

        UserEntity savedUser =  userRepository.save(newUser);

        return "User registered successfully";
    }
}
