package com.mindali.songs.clr;

import com.mindali.songs.service.PlaylistService;
import com.mindali.songs.service.SongService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
@RequiredArgsConstructor
public class TestService implements CommandLineRunner {
    private final PlaylistService playlistService;
    @Override
    public void run(String... args) throws Exception {
        System.out.println("########   Service Methods ########");
        try {
            String playlistName = "TestService1";
            String songId1 = "CPEBN2dVNUY";
            String songId2 = "lPXWt2ESxVY";

            System.out.println("*** Method: Create Playlist ***");
            System.out.print("Added playlist? ");
            System.out.println(playlistService.CreatePlaylist(playlistName));
            int playlistId = playlistService.GetPlaylistIdByName(playlistName);
            System.out.println();

            System.out.println("*** Method: Add Song ***");
            System.out.print("Added song "+ songId1 +" ?");
            System.out.println(playlistService.AddSong(playlistId, songId1));
            System.out.print("Added song "+ songId2 +" ?");
            System.out.println(playlistService.AddSong(playlistId, songId2));
            System.out.println();

            System.out.println("*** Method: Remove Song ***");
            System.out.print("Removed song "+ songId2 +" ?");
            System.out.println(playlistService.RemoveSong(playlistId, songId2));
            System.out.println();

            System.out.println("*** Method: Clear Playlist***");
            System.out.print("Cleared playlist "+playlistName+" ?");
            System.out.println(playlistService.ClearPlaylist(playlistId));
            System.out.println();

        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
