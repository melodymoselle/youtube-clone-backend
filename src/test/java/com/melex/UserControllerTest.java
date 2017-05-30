package com.melex;

import com.melex.api.UserController;
import com.melex.data.UserService;
import com.melex.models.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {

    private UserService userService;
    private UserController userController;
    private MockMvc mockMvc;

    @Before
    public void before(){
        userService = mock(UserService.class);
        userController = new UserController(userService);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @After
    public void after(){

    }

    @Test
    public void shouldReturnAllUsers() throws Exception{
        List<User> expectedUserList = createUserList(10);
        when(userService.findAll()).thenReturn(expectedUserList);

        mockMvc.perform(get("/api/users"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$", hasSize(2)));

        verify(userService, times(1)).findAll();
    }

    private List<User> createUserList(int count) {
        List<User> users = new ArrayList<User>();
        for (int i = 0; i < count; i++) {
            users.add(new User(i, "Email " + i, "Username " + i, "Password " + i));
        }
        return users;
    }
}
