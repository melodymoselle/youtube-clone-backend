package youtube;

import youtube.api.VideoController;
import youtube.data.VideoRepository;
import youtube.models.User;
import youtube.models.Video;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(value = VideoController.class)
public class VideoControllerTest {

    private static final int ID = 123;
    private static final String TITLE = "Some Video Title";
    private static final User AUTHOR = new User();

    private static final Video UNSAVED = new Video(TITLE, AUTHOR);
    private static final Video SAVED = new Video(ID, TITLE, AUTHOR);

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VideoRepository videoRepository;

    @Before
    public void before(){
    }

    @Test
    public void shouldReturnAllVideos() throws Exception {
        List<Video> expectedVideos = createVideoList(10);
        when(videoRepository.findAll()).thenReturn(expectedVideos);

        mockMvc.perform(get("/api/videos"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$", hasSize(10)));

        verify(videoRepository, times(1)).findAll();
    }

    @Test
    public void shouldReturnAllVideosByUsername() throws Exception {
        List<Video> expectedVideos = createVideoList(10);
        when(videoRepository.findAllByUsername("uname")).thenReturn(expectedVideos);

        mockMvc.perform(get("/api/videos?username=" + "uname"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$", hasSize(10)));

        verify(videoRepository, times(1)).findAllByUsername("uname");
    }

    @Test
    public void shouldReturnVideoById() throws Exception {
        when(videoRepository.findById(ID)).thenReturn(SAVED);

        mockMvc.perform(get("/api/videos/"+ID))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id", is(ID)))
                .andExpect(jsonPath("$.title", is(TITLE)))
                .andExpect(jsonPath("$.author", notNullValue()));

        verify(videoRepository, times(1)).findById(ID);
    }



    private List<Video> createVideoList(int count) {
        List<Video> videos = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            videos.add(new Video(i, TITLE + i, AUTHOR));
        }
        return videos;
    }
}
