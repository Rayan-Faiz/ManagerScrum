package com.iir4.managerscrum4iir.UserStory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class UserStoryController {

    @Autowired
    private UserStoryRepository userStoryRepository;

    @GetMapping("/userStory/{id}/edit")
    @ResponseBody
    public UserStory getUserStoryById(@PathVariable Long id) {
        Optional<UserStory> optionalUserStory = userStoryRepository.findById(id);
        return optionalUserStory.orElse(null);
    }
}
