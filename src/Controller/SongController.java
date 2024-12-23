package Controller;

import Entity.Song;
import Service.SongService;

import java.util.List;


public class SongController {
    private SongService service = new SongService();

    public void addSong(String id, String title, String artist, String author) {
        service.addSong(new Song(id, title, artist, author));
//        System.out.println("Song added successfully");
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
        System.out.println("song saved successfully");
    }

    public List<Song> searchSong(String title) {
        List<Song> results = service.searchSong(title);
        if (results.isEmpty()) {
            System.out.println("Không tìm thấy kết quả phù hợp: " + title);
//        } else {
//            System.out.println("Kết quả tìm kiếm bài hát: ");
            for (Song song : results) {
                System.out.println(song);
            }
        }
        return results;
    }
}
