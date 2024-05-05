package com.iir4.managerscrum4iir.UserStory;

import com.iir4.managerscrum4iir.Users.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserStoryController {

    @Autowired
    private UserStoryRepository userStoryRepository;

    @GetMapping("/userStory")
    public List<UserStory> getAllUsers() {
        return userStoryRepository.findAll();
    }

    @DeleteMapping("/userStory/{id}/delete")
    public void deleteUser(@PathVariable Long id) {
        userStoryRepository.deleteById(id);
    }

    @GetMapping("/userStory/{id}/edit")
    @ResponseBody
    public UserStory getUserStoryById(@PathVariable Long id) {
        Optional<UserStory> optionalUserStory = userStoryRepository.findById(id);
        return optionalUserStory.orElse(null);
    }
}
