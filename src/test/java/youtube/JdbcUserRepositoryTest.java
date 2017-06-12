package youtube;

import youtube.data.JdbcUserRepository;
import youtube.models.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JdbcUserRepositoryTest {

    @Autowired
    private JdbcUserRepository testRepo;

    @Before
    public void before(){
    }

    @Test
    public void shouldReturnAllUsers() throws Exception{
        List<User> users = testRepo.findAll();

        assertEquals(users.size(), 3);
        assertEquals(users.get(0).getId(), 1);
        assertEquals(users.get(1).getId(), 2);
        assertEquals(users.get(2).getId(), 3);
    }

    @Test
    public void shouldReturnUserById() throws Exception{
        User user = testRepo.findOne(1);

        assertEquals(user.getEmail(), "email1");
        assertEquals(user.getUsername(), "username1");
        assertEquals(user.getPassword(), "password1");
    }

    @Test
    public void shouldReturnUserByUsername() throws Exception{
        User user = testRepo.findOne("username1");

        assertEquals(user.getId(), 1);
        assertEquals(user.getEmail(), "email1");
        assertEquals(user.getPassword(), "password1");
    }

    @Test
    public void shouldRegisterNewUser() throws Exception{
        User user = new User("email4", "username4", "password4");
        user = testRepo.register(user);

        assertNotNull(user);
        assertEquals(user.getId(), 4);
    }

    @Test
    public void shouldFailRegisterReturnNull() throws Exception{
        User user = new User("email1", "username1", "password1");
        user = testRepo.register(user);

        assertNull(user);
    }
}
