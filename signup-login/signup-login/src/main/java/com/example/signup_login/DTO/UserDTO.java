package com.example.signup_login.DTO;

import lombok.*;

//@AllArgsConstructor
//@NoArgsConstructor
//@Getter
//@Setter
@Data
public class UserDTO {

    private Long Id;
    private String name;
    private String userName;
    private String email;
    private String password;

//    public String getEmail() {
//        return email;
//    }


    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
