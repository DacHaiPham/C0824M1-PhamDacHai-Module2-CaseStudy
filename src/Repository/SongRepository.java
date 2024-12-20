package Repository;

import Entity.Song;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SongRepository {
    private List<Song> songs = new ArrayList<Song>();

    public void addSong(Song song){
        songs.add(song);
    }

    public List<Song> getAllSongs(){
        return songs;
    }

    public Song findById(String id){
        return songs.stream().filter(song -> song.getId().equals(id)).findFirst().orElse(null);
    }

    public List<Song> findByTitle(String title){
        return songs.stream().filter(song -> song.getTitle().toLowerCase().contains(title.toLowerCase())).toList();
    }

    public boolean removeSong(String id){
        return songs.removeIf(song -> song.getId().equals(id));
    }

    public void updateSong(Song updatedSong){
        Song existingSong = findById(updatedSong.getId());
        if(existingSong != null){
            existingSong.setTitle(updatedSong.getTitle());
            existingSong.setArtist(updatedSong.getArtist());
            existingSong.setAuthor(updatedSong.getAuthor());
        }
    }

    public void saveSong(Song song) {
        File file = new File("src/data/Song.csv");
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true))) {
            String songData = song.getId() + "," +
                    song.getTitle() + "," +
                    song.getArtist() + "," +
                    song.getAuthor();
            bufferedWriter.write(songData);
            bufferedWriter.newLine();
        } catch (IOException e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }

    public List<Song> loadSongs() {
        File file = new File("src/data/Song.csv");
        List<Song> songs = new ArrayList<>();

        if (!file.exists()) {
            System.out.println("File does not exist. Returning empty list.");
            return songs;
        }

        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                // Phân tách dòng thành các phần dựa trên dấu phẩy
                String[] songData = line.split(",");
                if (songData.length == 4) { // Đảm bảo đúng định dạng
                    Song song = new Song(
                            songData[0], // ID
                            songData[1], // Title
                            songData[2], // Artist
                            songData[3]  // Author
                    );
                    songs.add(song);
                }
            }
            bufferedReader.close();
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return songs;
    }
}
