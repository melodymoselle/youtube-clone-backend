package com.melex;

import com.melex.data.UserService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {

    MockMvc mockMvc;

    @Autowired
    WebApplicationContext wap;

    @Autowired
    UserService userService;

    @Before
    public void before(){
        mockMvc = MockMvcBuilders.webAppContextSetup(wap).build();
    }

    @After
    public void after(){

    }

    @Test
    public void shouldReturnAllUsers(){

    }
}
