package Entity;

public class Song {
    private String id;
    private String title;
    private String author;
    private String artist;

    public Song(String id, String title, String author, String artist) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.artist = artist;
    }

    //Getters and Setters
    public String getId() { return id; }
    public void setId(Long id) { this.id = String.valueOf(id); }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }
    public String getArtist() { return artist; }
    public void setArtist(String artist) { this.artist = artist; }


    @Override
    public String toString() {
        return "ID: " + id + ", Title: " + title + ", Artist: " + artist + ", Author: " + author;
    }

}
