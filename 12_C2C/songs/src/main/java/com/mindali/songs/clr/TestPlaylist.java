package com.mindali.songs.clr;

import com.mindali.songs.beans.YoutubeData;
import com.mindali.songs.service.PlaylistService;
import com.mindali.songs.service.SongService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
//@Component
@RequiredArgsConstructor
public class TestPlaylist implements CommandLineRunner {
    private final SongService songService;
    private final PlaylistService playlistService;
    @Value("${youtube.api}")
    private String API_KEY;
    private String URL = "https://www.googleapis.com/youtube/v3/videos?part=snippet&key=AIzaSyB_rfFikBTjch9lqoaaLuqX_ETqsGGTvN8&id=";
    @Override
    public void run(String... args) throws Exception {
        String[] songId = {"lPXWt2ESxVY","ZbZSe6N_BXs"};
        YoutubeData songData1 = songService.getSongData(songId[0]);
        YoutubeData songData2 = songService.getSongData(songId[1]);

        String name = "Roni's Playlist2";
        playlistService.CreatePlaylist(name);
        int playListId = playlistService.GetPlaylistIdByName(name);
        playlistService.AddSong(playListId,songData1.getId());
        playlistService.AddSong(playListId,songData2.getId());
    }
}
