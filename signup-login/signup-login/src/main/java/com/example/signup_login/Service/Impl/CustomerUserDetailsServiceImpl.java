package com.example.signup_login.Service.Impl;

import com.example.signup_login.Entity.UserEntity;
import com.example.signup_login.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomerUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUserName(username);

        if(user == null){
            System.out.printf("User was not found");
            throw new UsernameNotFoundException("User was not found");
        }
        return  user;
    }
}
