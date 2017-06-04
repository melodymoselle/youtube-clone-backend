package com.melex;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.melex.api.UserController;
import com.melex.data.UserRepository;
import com.melex.models.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(value = UserController.class)
public class UserControllerTest {

    private static final String EMAIL = "iamgroot@gmail.com";
    private static final String UNAME = "iamgroot";
    private static final String PWORD = "iamgroot";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository userRepository;

    @Before
    public void before(){
    }

    @After
    public void after(){

    }

    @Test
    public void shouldReturnAllUsers() throws Exception{
        List<User> expectedUserList = createUserList(10);
        when(userRepository.findAll()).thenReturn(expectedUserList);

        mockMvc.perform(get("/api/users"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$", hasSize(10)));

        verify(userRepository, times(1)).findAll();
    }

    @Test
    public void shouldProcessRegistrationAndReturnUserWithId() throws Exception{
        User unsaved = new User(EMAIL, UNAME, PWORD);
        User saved = new User(10L, EMAIL, UNAME, PWORD);
        String json = new ObjectMapper().writeValueAsString(unsaved);
        //Need to override User equals() method??
        when(userRepository.register(unsaved)).thenReturn(saved);
        mockMvc.perform(post("/api/users/register")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(json))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(10L)))
                .andExpect(jsonPath("$[0].email", is(EMAIL)))
                .andExpect(jsonPath("$[0].username", is(UNAME)))
                .andExpect(jsonPath("$[0].password", is(PWORD)));

        verify(userRepository, times(1)).register(unsaved);
    }

    private List<User> createUserList(int count) {
        List<User> users = new ArrayList<User>();
        for (int i = 0; i < count; i++) {
            users.add(new User(i, "Email " + i, "Username " + i, "Password " + i));
        }
        return users;
    }
}
