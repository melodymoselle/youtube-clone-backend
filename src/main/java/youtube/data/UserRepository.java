package youtube.data;

import youtube.models.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserRepository {
    List<User> findAll();
    List<User> findAll(int count);
    User findOne(int id);
    User findOne(String username);
    User register(User user);
}
