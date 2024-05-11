package com.iir4.managerscrum4iir.Dashboard;

import com.iir4.managerscrum4iir.Sprint.Sprint;
import com.iir4.managerscrum4iir.Sprint.SprintRepository;
import com.iir4.managerscrum4iir.Task.Task;
import com.iir4.managerscrum4iir.Task.TaskRepository;
import com.iir4.managerscrum4iir.UserStory.UserStory;
import com.iir4.managerscrum4iir.UserStory.UserStoryRepository;
import com.iir4.managerscrum4iir.Users.Users;
import com.iir4.managerscrum4iir.Users.UsersRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class DashboardControllerTest {

    @Mock
    private UserStoryRepository userStoryRepositoryMock;

    @Mock
    private SprintRepository sprintRepositoryMock;

    @Mock
    private TaskRepository taskRepositoryMock;

    @Mock
    private UsersRepository usersRepositoryMock;

    @Mock
    private Model modelMock;

    @InjectMocks
    private DashboardController dashboardController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testMemberDashboard() {
        List<Users> users = new ArrayList<>();
        List<Sprint> sprints = new ArrayList<>();
        List<Task> tasks = new ArrayList<>();
        List<UserStory> userStories = new ArrayList<>();

        when(usersRepositoryMock.findAll()).thenReturn(users);
        when(sprintRepositoryMock.findAll()).thenReturn(sprints);
        when(taskRepositoryMock.findAll()).thenReturn(tasks);
        when(userStoryRepositoryMock.findAll()).thenReturn(userStories);

        String viewName = dashboardController.memberDashboard(modelMock);

        assertEquals("member-dashboard", viewName);

        verify(usersRepositoryMock, times(1)).findAll();
        verify(sprintRepositoryMock, times(1)).findAll();
        verify(taskRepositoryMock, times(1)).findAll();
        verify(userStoryRepositoryMock, times(1)).findAll();

        verify(modelMock, times(1)).addAttribute("users", users);
        verify(modelMock, times(1)).addAttribute("userStories", userStories);
        verify(modelMock, times(1)).addAttribute("sprints", sprints);
        verify(modelMock, times(1)).addAttribute("tasks", tasks);
    }

    @Test
    public void testScrumMasterDashboard() {
        List<Users> users = new ArrayList<>();
        List<Sprint> sprints = new ArrayList<>();
        List<Task> tasks = new ArrayList<>();
        List<UserStory> userStories = new ArrayList<>();

        when(usersRepositoryMock.findAll()).thenReturn(users);
        when(sprintRepositoryMock.findAll()).thenReturn(sprints);
        when(taskRepositoryMock.findAll()).thenReturn(tasks);
        when(userStoryRepositoryMock.findAll()).thenReturn(userStories);

        String viewName = dashboardController.scrumMasterDashboard(modelMock);

        assertEquals("scrum-master-dashboard", viewName);

        verify(usersRepositoryMock, times(1)).findAll();
        verify(sprintRepositoryMock, times(1)).findAll();
        verify(taskRepositoryMock, times(1)).findAll();
        verify(userStoryRepositoryMock, times(1)).findAll();

        verify(modelMock, times(1)).addAttribute("users", users);
        verify(modelMock, times(1)).addAttribute("userStories", userStories);
        verify(modelMock, times(1)).addAttribute("sprints", sprints);
        verify(modelMock, times(1)).addAttribute("tasks", tasks);
    }
}
