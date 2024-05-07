package com.iir4.managerscrum4iir.Sprint;

import com.iir4.managerscrum4iir.Sprint.Sprint;
import com.iir4.managerscrum4iir.Task.Task;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class SprintWithTasksDTO {
    private Sprint sprint;
    private List<Task> tasks;

}
