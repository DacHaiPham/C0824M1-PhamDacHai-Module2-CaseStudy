package Controller;

import Entity.Song;
import Service.SongService;

import java.util.List;

public class SongController {
    private SongService service = new SongService();

    public void addSong(String id, String title, String artist, String author) {
        service.addSong(new Song(id, title, artist, author));
    }

    public List<Song> getAllSongs() {
        return service.getAllSongs();
    }

    public Song findById(String id) {
        return service.findById(id);
    }

    public List<Song> searchByTitle(String title) {
        return service.searchByTitle(title);
    }

    public boolean removeSong(String id) {
        return service.removeSong(id);
    }

    public void updateSong(String id, String title, String artist, String author) {
        service.updateSong(new Song(id, title, artist, author));
    }

    public void saveSong(String id, String title, String artist, String author) {
        Song song = new Song(id, title, artist, author);
        service.saveSong(song);
    }


    public List<Song> displayAllSongs() {
        List<Song> songs = service.getAllSongs();
        if (songs.isEmpty()) {
            System.out.println("No songs found.");
        } else {
            System.out.println("List of Songs:");
            for (Song song : songs) {
                System.out.println(song);
            }
        }
        return songs;
    }

}
