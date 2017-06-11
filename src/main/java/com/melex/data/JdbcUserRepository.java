package com.melex.data;

import com.melex.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
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
        String query = "SELECT * FROM 'users' WHERE 'id' = ?";
        return jdbcTemplate.queryForObject(query, new UserRowMapper(), id);
    }

    @Override
    public User findOne(String username) {
        String query = "SELECT * FROM 'users' WHERE 'username' = ?";
        return jdbcTemplate.queryForObject(query, new UserRowMapper(), username);
    }

    @Override
    public User register(User user) {
        String sql = "INSERT INTO 'users' ('email', 'username', 'password') VALUES (?,?,?)";
        GeneratedKeyHolder keys = new GeneratedKeyHolder();
        int rowsAffected =
                jdbcTemplate.update(sql,
                    user.getEmail(),
                    user.getUsername(),
                    user.getPassword(),
                        keys);

        if (rowsAffected == 1){
            user.setId(keys.getKey().intValue());
            return user;
        }

        return null;
    }

    private static final class UserRowMapper implements RowMapper<User>{
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new User(
                    rs.getInt("id"),
                    rs.getString("email"),
                    rs.getString("username"),
                    rs.getString("password"));
        }
    }
}
