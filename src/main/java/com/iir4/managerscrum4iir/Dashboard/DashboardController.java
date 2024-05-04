package com.iir4.managerscrum4iir.Dashboard;

import com.iir4.managerscrum4iir.Sprint.Sprint;
import com.iir4.managerscrum4iir.Sprint.SprintRepository;
import com.iir4.managerscrum4iir.Task.Task;
import com.iir4.managerscrum4iir.Task.TaskRepository;
import com.iir4.managerscrum4iir.UserStory.UserStory;
import com.iir4.managerscrum4iir.UserStory.UserStoryRepository;
import com.iir4.managerscrum4iir.Users.Users;
import com.iir4.managerscrum4iir.Users.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    private final UserStoryRepository userStoryRepository;
    private final SprintRepository sprintRepository;
    private final TaskRepository taskRepository;
    private final UsersRepository usersRepository;

    @Autowired
    public DashboardController(UserStoryRepository userStoryRepository, SprintRepository sprintRepository, TaskRepository taskRepository, UsersRepository usersRepository) {
        this.userStoryRepository = userStoryRepository;
        this.sprintRepository = sprintRepository;
        this.taskRepository = taskRepository;
        this.usersRepository = usersRepository;
    }

    @GetMapping("/member-dashboard")
    public String memberDashboard(Model model) {
        List<Users> users = usersRepository.findAll();
        List<Sprint> sprints = sprintRepository.findAll();
        List<Task> tasks = taskRepository.findAll();
        List<UserStory> userStories = userStoryRepository.findAll();
        model.addAttribute("users", users);
        model.addAttribute("userStories", userStories);
        model.addAttribute("sprints", sprints);
        model.addAttribute("tasks", tasks);
        return "member-dashboard";
    }

    @GetMapping("/scrum-master-dashboard")
    public String scrumMasterDashboard() {
        return "scrum-master-dashboard";
    }

}

