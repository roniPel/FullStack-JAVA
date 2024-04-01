package com.mindali.songs.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mindali.songs.beans.YoutubeData;
import com.mindali.songs.service.SongService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("Songs/")
@RequiredArgsConstructor
public class YouTubeController {
    private final SongService service;
    @GetMapping("GetSongData/{id}")
    @ResponseStatus(HttpStatus.OK)
    public YoutubeData GetSongData(@PathVariable String id) throws JsonProcessingException {
        return service.getSongData(id);
    }

    @GetMapping(value = {"/GetAllSongs"})
    public List<YoutubeData> GetAllSongs(){
        return service.GetAllSongs();
    }
}
