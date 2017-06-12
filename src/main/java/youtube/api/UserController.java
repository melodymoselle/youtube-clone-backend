package youtube.api;

import youtube.data.JdbcUserRepository;
import youtube.data.UserRepository;
import youtube.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping(value = "/register", method = POST)
    public User register(@RequestBody User user) {
        return userRepository.register(user);
    }


}
