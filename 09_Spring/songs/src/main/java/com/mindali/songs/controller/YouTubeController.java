package com.mindali.songs.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mindali.songs.beans.YoutubeData;
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
    private final SongService service;
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public YoutubeData getSongData(@PathVariable String id) throws JsonProcessingException {
        return service.getSongData(id);
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<YoutubeData> getAllSongs() {
        return service.getAllSongs();
    }

    @GetMapping("/add/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void addSong(@PathVariable String id) throws JsonProcessingException{
        //call the service

    }
}
