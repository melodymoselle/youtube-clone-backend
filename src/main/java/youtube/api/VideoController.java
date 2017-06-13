package youtube.api;

import youtube.data.VideoRepository;
import youtube.models.Video;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping("/api/videos")
public class VideoController {

    @Autowired
    private VideoRepository videoRepository;

    @RequestMapping(method = GET)
    public List<Video> getAllVideos(@RequestParam(value = "username", required = false) String username){
        if (username != null){
            return videoRepository.findAllByUsername(username);
        }
        return videoRepository.findAll();
    }

    @RequestMapping(value = "/{id}", method = GET)
    public Video getVideoById(@PathVariable("id") int id){
        return videoRepository.findById(id);
    }

}
