package youtube.data;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.PreparedStatementCreator;
import youtube.exceptions.UsernameExistsException;
import youtube.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class JdbcUserRepository implements UserRepository{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<User> findAll() {
        String sql = "SELECT * FROM users";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));
    }

    @Override
    public List<User> findAll(int count) {
        return null;
    }

    @Override
    public User findOne(int id) {
        String sql = "SELECT * FROM users WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new UserRowMapper(), id);
    }

    @Override
    public User findOne(String username) {
        String sql = "SELECT * FROM users WHERE username = ?";
        return jdbcTemplate.queryForObject(sql, new UserRowMapper(), username);
    }

    @Override
    public User register(User user) {
        String sql = "INSERT INTO users (email, username, password) VALUES (?,?,?)";
        GeneratedKeyHolder keys = new GeneratedKeyHolder();

        try {
            jdbcTemplate.update(
            new PreparedStatementCreator() {
                public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql, new String[] { "id" });
                ps.setString(1, user.getEmail());
                ps.setString(2, user.getUsername());
                ps.setString(3, user.getPassword());
                return ps;
                }
        }, keys);}
        catch (DuplicateKeyException e){
            throw new UsernameExistsException(user.getUsername());
        }
        user.setId(keys.getKey().intValue());

        return user;
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
