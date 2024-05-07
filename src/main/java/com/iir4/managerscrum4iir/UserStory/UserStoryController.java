package com.iir4.managerscrum4iir.UserStory;

import com.iir4.managerscrum4iir.Users.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/userStory")
public class UserStoryController {

    @Autowired
    private UserStoryRepository userStoryRepository;

    // Method to add a new user story
    @PostMapping("/add")
    public UserStory addUserStory(@RequestBody UserStory userStory) {
        return userStoryRepository.save(userStory);
    }

    // Method to update an existing user story
    @PutMapping("/{id}/update")
    public UserStory updateUserStory(@PathVariable Long id, @RequestBody UserStory userStoryDetails) {
        Optional<UserStory> optionalUserStory = userStoryRepository.findById(id);
        if (optionalUserStory.isPresent()) {
            UserStory userStory = optionalUserStory.get();
            userStory.setTitle(userStoryDetails.getTitle());
            userStory.setDescription(userStoryDetails.getDescription());
            return userStoryRepository.save(userStory);
        } else {
            return null;
        }
    }

    // Method to delete a user story
    @DeleteMapping("/{id}/delete")
    public void deleteUserStory(@PathVariable Long id) {
        userStoryRepository.deleteById(id);
    }

    // Method to get a user story by id
    @GetMapping("/{id}/select")
    public UserStory getUserStoryById(@PathVariable Long id) {
        Optional<UserStory> optionalUserStory = userStoryRepository.findById(id);
        return optionalUserStory.orElse(null);
    }

    // Method to get all user stories
    @GetMapping("/getAll")
    public List<UserStory> getAllUserStories() {
        return userStoryRepository.findAll();
    }
}
