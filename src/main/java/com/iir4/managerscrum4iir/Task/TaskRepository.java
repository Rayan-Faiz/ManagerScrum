package com.iir4.managerscrum4iir.Task;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findBySprintId(Long sprintId);

    @Transactional
    void deleteTasksBySprintId(Long id);
}
