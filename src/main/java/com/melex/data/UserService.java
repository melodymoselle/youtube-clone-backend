package com.melex.data;

import com.melex.models.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    List<User> findAll(long max, int count);
    User findById(long id);
    User findByUsername(String username);
    User register(User user);
    User update(User user);
}
