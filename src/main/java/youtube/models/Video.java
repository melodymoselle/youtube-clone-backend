package youtube.models;

public class Video {
    private int id;
    private String title;
    private User author;

    public Video() {
    }

    public Video(String title, User author) {
        this.title = title;
        this.author = author;
    }

    public Video(int id, String title, User author) {
        this.id = id;
        this.title = title;
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Video video = (Video) o;

        if (id != video.id) return false;
        return title != null ? title.equals(video.title) : video.title == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        return result;
    }
}
