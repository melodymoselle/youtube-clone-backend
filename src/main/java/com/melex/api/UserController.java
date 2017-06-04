package com.melex.api;

import com.melex.data.UserRepository;
import com.melex.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(method = GET)
    public List<User> users() {
        return userRepository.findAll();
    }

    @RequestMapping(value = "/register", method = POST)
    public User register() {

    }
}
