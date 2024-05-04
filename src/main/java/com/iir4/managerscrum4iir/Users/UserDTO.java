package com.iir4.managerscrum4iir.Users;

import com.iir4.managerscrum4iir.Roles.Roles;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Setter
@Getter
public class UserDTO {
    private String name;
    private String email;
    private String password;
    private List<Roles> roles;

    public UserDTO() {
    }

    public UserDTO(String name, String email, String password, List<Roles> roles) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

}
