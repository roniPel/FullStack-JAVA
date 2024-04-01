package com.mindali.songs.clr;

import com.mindali.songs.beans.PlayList;
import com.mindali.songs.beans.YoutubeData;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class TestRest implements CommandLineRunner {
    private final RestTemplate restTemplate;
    @Override
    public void run(String... args) throws Exception {
        try {
            String playlistName = "Test4";
            String songId = "lPXWt2ESxVY";
            CreatePlaylist(playlistName);
            AddSong(songId,playlistName);
            RemoveSong(songId,playlistName);
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void RemoveSong(String songId, String name) {
        System.out.println("*** Method: Remove Song ***");

        // Delete song
        restTemplate.delete("http://localhost:8080/PlayLists/RemoveSong/"+songId+"/"+name);
        System.out.println("Deleted Song? true");
        System.out.println();
    }

    private void AddSong(String songId, String name) {
        System.out.println("*** Method: Add Song ***");

        YoutubeData youtubeData = restTemplate.getForObject
                ("http://localhost:8080/Songs/GetSongData/"+songId,YoutubeData.class);
        // Add song to playlist
        ResponseEntity<String> responsePost = restTemplate.postForEntity
                ("http://localhost:8080/PlayLists/AddSong/"+songId+"/"+name,youtubeData,String.class);
        System.out.print("Added Song? ");
        System.out.println(responsePost.getStatusCode().value()== HttpStatus.CREATED.value()?"true":"false");
        System.out.println();
    }

    private void CreatePlaylist(String name) {
        System.out.println("*** Method: Add PlayList ***");
        // Create new playlist
        PlayList playList = PlayList.builder()
                .playlistName(name)
                .build();

        // Add playlist to DB
        System.out.println("Playlist to add: ");
        System.out.print(playList);
        ResponseEntity<String> responsePost = restTemplate.postForEntity
                ("http://localhost:8080/PlayLists/CreatePlaylist",playList,String.class);
        System.out.print("Added Playlist? ");
        System.out.println(responsePost.getStatusCode().value()== HttpStatus.CREATED.value()?"true":"false");
        System.out.println();
    }
}
