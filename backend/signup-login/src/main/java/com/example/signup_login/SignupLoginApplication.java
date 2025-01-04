package com.example.signup_login;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.modelmapper.ModelMapper;

@SpringBootApplication
public class SignupLoginApplication {

//	@Bean
//	public ModelMapper modelMapper(){
//		return new ModelMapper();
//	}

	public static void main(String[] args) {

		SpringApplication.run(SignupLoginApplication.class, args);
		System.out.println("=========== success ============");
	}

}
