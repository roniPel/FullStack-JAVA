package com.mindali.songs.clr;

import com.mindali.songs.beans.YoutubeData;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@Order(2)
@RequiredArgsConstructor
public class TestRest implements CommandLineRunner {
    private final RestTemplate restTemplate;
    private String playlistName = "TestRest1";
    private String songId1 = "CPEBN2dVNUY";
    private String songId2 = "lPXWt2ESxVY";
    @Override
    public void run(String... args) throws Exception {
        System.out.println("########   Rest Methods ########");
        try {
            CreatePlaylist(playlistName);
            AddSong(songId1,playlistName);
            AddSong(songId2,playlistName);
            RemoveSong(songId1,playlistName);
            ClearPlaylist(playlistName);
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void ClearPlaylist(String name) {
        System.out.println("*** Method: Clear Playlist ***");
        Integer playlistId = restTemplate.getForObject
                ("http://localhost:8080/PlayLists/GetPlayList/"+name,Integer.class);

        ResponseEntity<String> responsePost = restTemplate.postForEntity
                ("http://localhost:8080/PlayLists/ClearPlayList/"+playlistId,name,String.class);
        System.out.print("Cleared Playlist "+name+"? ");
        System.out.println(responsePost.getStatusCode().value()== HttpStatus.CREATED.value()?"true":"false");
        System.out.println();
    }

    private void RemoveSong(String songId, String name) {
        System.out.println("*** Method: Remove Song ***");
        Integer playlistId = restTemplate.getForObject
                ("http://localhost:8080/PlayLists/GetPlayList/"+name,Integer.class);
        // Delete song
        restTemplate.delete("http://localhost:8080/PlayLists/RemoveSong/"+playlistId+"/"+songId);
        System.out.println("Deleted Song "+songId+"? true");
        System.out.println();
    }

    private void AddSong(String songId, String name) {
        System.out.println("*** Method: Add Song ***");
        Integer playlistId = restTemplate.getForObject
                ("http://localhost:8080/PlayLists/GetPlayList/"+name,Integer.class);

        YoutubeData youtubeData = restTemplate.getForObject
                ("http://localhost:8080/Songs/GetSongData/"+songId,YoutubeData.class);
        // Add song to playlist
        ResponseEntity<String> responsePost = restTemplate.postForEntity
                ("http://localhost:8080/PlayLists/AddSong/"+playlistId+"/"+songId,name,String.class);
        System.out.print("Added Song"+songId+"? ");
        System.out.println(responsePost.getStatusCode().value()== HttpStatus.CREATED.value()?"true":"false");
        System.out.println();
    }

    private void CreatePlaylist(String name) {
        System.out.println("*** Method: Add PlayList ***");

        // Add playlist to DB
        ResponseEntity<String> responsePost = restTemplate.postForEntity
                ("http://localhost:8080/PlayLists/CreatePlaylist",name,String.class);
        System.out.print("Added Playlist "+name+"? ");
        System.out.println(responsePost.getStatusCode().value()== HttpStatus.CREATED.value()?"true":"false");
        System.out.println();
    }
}
