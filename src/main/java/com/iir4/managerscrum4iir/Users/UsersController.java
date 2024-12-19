package com.iir4.managerscrum4iir.Users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UsersController {

    @Autowired
    private UsersRepository usersRepository;

    @GetMapping("/users")
    public List<Users> getAllUsers() {
        return usersRepository.findAll();
    }

    @DeleteMapping("/users/{id}/delete")
    public void deleteUser(@PathVariable Long id) {
        usersRepository.deleteById(id);
    }
}
