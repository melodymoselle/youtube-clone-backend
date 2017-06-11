package com.melex.data;

import com.melex.models.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserRepository {
    List<User> findAll();
    List<User> findAll(int count);
    User findOne(int id);
    User findByUsername(String username);
    User register(User user);
    User update(User user);
}
