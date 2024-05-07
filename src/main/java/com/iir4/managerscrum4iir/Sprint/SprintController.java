package com.iir4.managerscrum4iir.Sprint;

import com.iir4.managerscrum4iir.Task.Task;
import com.iir4.managerscrum4iir.Task.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/sprints")
public class SprintController {

    @Autowired
    private SprintRepository sprintRepository;
    @Autowired
    private TaskRepository taskRepository;

    @PostMapping("/add")
    public Sprint addSprintWithTasks(@RequestBody SprintWithTasksDTO sprintWithTasksDTO) {
        Sprint sprint = sprintWithTasksDTO.getSprint();
        Sprint savedSprint = sprintRepository.save(sprint);

        List<Task> tasks = sprintWithTasksDTO.getTasks();
        tasks.forEach(task -> task.setSprint(savedSprint));
        taskRepository.saveAll(tasks);

        return savedSprint;
    }

    @PutMapping("/{id}/update")
    public Sprint updateSprint(@PathVariable Long id, @RequestBody SprintWithTasksDTO sprintWithTasksDTO) {
        Optional<Sprint> optionalSprint = sprintRepository.findById(id);
        if (optionalSprint.isEmpty()) {
            return null;
        }

        Sprint existingSprint = optionalSprint.get();
        taskRepository.deleteTasksBySprintId(existingSprint.getId());

        existingSprint.setName(sprintWithTasksDTO.getSprint().getName());
        existingSprint.setStartDate(sprintWithTasksDTO.getSprint().getStartDate());
        existingSprint.setEndDate(sprintWithTasksDTO.getSprint().getEndDate());

        existingSprint.getTasks().clear();

        for (Task task : sprintWithTasksDTO.getTasks()) {
            task.setSprint(existingSprint);
            taskRepository.save(task);
        }

        return sprintRepository.save(existingSprint);
    }


    @DeleteMapping("/{id}/delete")
    public void deleteSprint(@PathVariable Long id) {
        sprintRepository.deleteById(id);
    }
}
