package youtube.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import youtube.data.JdbcUserRepository;
import youtube.data.UserRepository;
import youtube.models.User;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private UserRepository userRepository = new JdbcUserRepository();

    @RequestMapping(value = "/login", method = POST)
    public Object login(Authentication auth){
        return auth.getPrincipal();
    }

    @RequestMapping(value = "/register", method = POST)
    @ResponseStatus(HttpStatus.CREATED)
    public User register(@RequestBody User user) {
        return userRepository.register(user);
    }
}
