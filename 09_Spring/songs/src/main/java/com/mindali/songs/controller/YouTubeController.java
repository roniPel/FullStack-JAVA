package com.mindali.songs.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mindali.songs.beans.YoutubeData;
import com.mindali.songs.service.PlayListService;
import com.mindali.songs.service.SongService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
@CrossOrigin()
public class YouTubeController {
    private final SongService songService;
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public YoutubeData getSongData(@PathVariable String id) throws JsonProcessingException {
        return songService.getSongData(id);
    }
    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<YoutubeData> getAllSongs() {
        return songService.getAllSongs();
    }
    @DeleteMapping("/removeSong/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void removeSong(@PathVariable String id) throws JsonProcessingException {
        //Todo - change so that playListId is provided by controller (via login/ JWT token/ etc..)
        songService.removeYouTubeData(id);
    }
}
