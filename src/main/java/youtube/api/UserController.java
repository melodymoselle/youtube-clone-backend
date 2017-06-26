package youtube.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import youtube.data.JdbcUserRepository;
import youtube.data.UserRepository;
import youtube.models.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository = new JdbcUserRepository();

    @RequestMapping(method = GET)
    public List<User> users() {
        return userRepository.findAll();
    }

    @RequestMapping(value = "/{username}", method = GET)
    public User userByUsername(@PathVariable ("username") String username){
        return userRepository.findOne(username);
    }



}
