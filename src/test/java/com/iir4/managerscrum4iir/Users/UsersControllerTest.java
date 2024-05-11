package com.iir4.managerscrum4iir.Users;

import com.iir4.managerscrum4iir.Roles.Roles;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = UsersController.class)
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class UsersControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsersRepository usersRepository;

    @InjectMocks
    private UsersController usersController;

    @Test
    @WithMockUser(username = "testuser", password = "testpassword", authorities = { "ROLE_Master" })
    public void testGetAllUsers() throws Exception {
        // Create a mock list of users with roles
        Roles role = new Roles(1L, "ROLE_Master");
        Users user1 = new Users(1L, "john@example.com", "John", "password", Arrays.asList(role));
        Users user2 = new Users(2L, "jane@example.com", "Jane", "password", Arrays.asList(role));
        List<Users> userList = Arrays.asList(user1, user2);

        // Mock the behavior of the repository
        when(usersRepository.findAll()).thenReturn(userList);

        // Perform the GET request and verify the response
        mockMvc.perform(get("/users")
                        .header(HttpHeaders.AUTHORIZATION, "Basic " + Base64.getEncoder().encodeToString("testuser:testpassword".getBytes())))
                .andExpect(status().isOk());

        // Verify that the repository method was called
        verify(usersRepository, times(1)).findAll();
    }
}
