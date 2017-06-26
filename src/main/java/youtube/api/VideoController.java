package youtube.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import youtube.data.VideoRepository;
import youtube.models.Video;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("/api/videos")
public class VideoController {

    private static final String UPLOADED_FILES = "videoFiles/";

    @Autowired
    private VideoRepository videoRepository;

    @RequestMapping(method = GET)
    public List<Video> getAllVideos(
        @RequestParam(value = "username", required = false) String username){
            if (username != null){
                return videoRepository.findAllByUsername(username);
            }
            return videoRepository.findAll();
        }

    @RequestMapping(value = "/{id}", method = GET)
    public Video getVideoById(@PathVariable("id") int id){
        return videoRepository.findById(id);
    }

    @RequestMapping(value = "/upload", method = POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Video uploadVideo(
        @RequestParam("file") MultipartFile file,
        @RequestParam("title") String title,
        @RequestParam("username") String username) throws IOException{
            saveFile(file, username);
            Video video = new Video(title, file.getOriginalFilename(), username);
            return videoRepository.save(video);
    }

    public Path saveFile(MultipartFile file, String username) throws IOException{
        Path dir = Paths.get(UPLOADED_FILES + username);
        Path path = Paths.get(dir + "/" + file.getOriginalFilename());
        byte[] bytes = file.getBytes();
        Files.createDirectories(dir);
        Path rs = Files.write(path, bytes);
        System.out.println(rs);
        return rs;
    }

}
