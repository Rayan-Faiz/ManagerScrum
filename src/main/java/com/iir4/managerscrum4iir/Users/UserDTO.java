package com.iir4.managerscrum4iir.Users;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
public class UserDTO {
    private String name;
    private String email;
    private String password;
    private Set<String> roles;

    // Constructors, getters, and setters

    public UserDTO() {
    }

    public UserDTO(String name, String email, String password, Set<String> roles) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

}
