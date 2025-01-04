package com.example.signup_login.Entity;

//import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
//@Setter
//@Getter
@Entity
@Table(name = "user")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id; // unique

    @Column(name = "name")
    private String name;

    @Column(name = "userName",unique = true)
    private String userName; //unique

    @Column(name = "email",unique = true)
    private String email; // unique

    @Column(name = "password")
    private String password;

    // Setter for email field
    public void setEmail(String email) {
        this.email = email;
    }

    // Other setters for the remaining fields
    public void setName(String name) {
        this.name = name;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setId(Long id){
        this.Id = id;
    }

    public Long getId() {
        return Id;
    }

    public String getName() {
        return name;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
