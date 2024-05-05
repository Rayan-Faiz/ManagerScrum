package com.iir4.managerscrum4iir.Sprint;

import com.iir4.managerscrum4iir.Task.Task;
import com.iir4.managerscrum4iir.Task.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SprintController {

    @Autowired
    private SprintRepository sprintRepository;
    @Autowired
    private TaskRepository taskRepository;

    @GetMapping("/sprint")
    public List<Sprint> getAllSprints() {
        return sprintRepository.findAll();
    }

    @DeleteMapping("/sprint/{id}/delete")
    public void deleteSprint(@PathVariable Long id) {
        Sprint sprint = sprintRepository.findById(id)
                .orElseThrow(ResourceNotFoundException::new);

        List<Task> tasks = taskRepository.findBySprintId(id);

        taskRepository.deleteAll(tasks);

        sprintRepository.delete(sprint);
    }
}

