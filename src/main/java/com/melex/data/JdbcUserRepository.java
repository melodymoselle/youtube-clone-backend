package com.melex.data;

import com.melex.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbcUserRepository implements UserRepository{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public List<User> findAll(int count) {
        return null;
    }

    @Override
    public User findOne(int id) {
        String query = "SELECT * FROM users WHERE "
        return null;
    }

    @Override
    public User findByUsername(String username) {
        return null;
    }

    @Override
    public User register(User user) {
        return null;
    }

    @Override
    public User update(User user) {
        return null;
    }
}
