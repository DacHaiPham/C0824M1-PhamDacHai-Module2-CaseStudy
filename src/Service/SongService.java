package Service;

import Entity.Song;
import Repository.SongRepository;

import java.util.List;

public class SongService {
    private SongRepository repository = new SongRepository();

    public void addSong(Song song) {
        repository.addSong(song);
        repository.saveSong(song);
    }

    public List<Song> getAllSongs() {
        return repository.getAllSongs();
    }

    public Song findById(String id) {
        return repository.findById(String.valueOf(id));
    }

    public List<Song> searchByTitle(String title) {
        return repository.findByTitle(title);
    }

    public boolean removeSong(String id) {
        return repository.removeSong(id);
    }

    public void updateSong(Song updatedSong) {
        repository.updateSong(updatedSong);
    }

    public void saveSong(Song song) {
        repository.saveSong(song); // Lưu bài hát vào file thông qua repository
    }

//    public List<Song> loadSongs() {
//        return repository.loadSongs(); // Trả về danh sách bài hát từ file
//    }

//    public SongService(SongRepository repository) {
//        this.repository = repository;
//    }

    public List<Song> searchSong(String title) {
        return repository.searchSong(title);
    }
}
