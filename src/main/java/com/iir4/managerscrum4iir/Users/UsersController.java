package com.iir4.managerscrum4iir.Users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsersController {

    @Autowired
    private UsersRepository usersRepository;

    @DeleteMapping("/users/{id}/delete")
    public void deleteUser(@PathVariable Long id) {
        System.out.println(id);
        usersRepository.deleteById(id);
    }
}
