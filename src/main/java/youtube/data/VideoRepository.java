package youtube.data;

import youtube.models.User;
import youtube.models.Video;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface VideoRepository {
    List<Video> findAll();
    List<Video> findAllByUser(User user);
    List<Video> findAllByUsername(String username);
    Video findById(long id);
    Video findByTitle(String title);

}
