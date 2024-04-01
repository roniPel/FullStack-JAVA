package com.mindali.songs.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mindali.songs.beans.YoutubeData;
import com.mindali.songs.exceptions.SongException;
import com.mindali.songs.service.PlaylistService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("PlayLists/")
@RequiredArgsConstructor
public class PlayListController {
    private final PlaylistService playListService;

    @GetMapping("/GetPlayList/{name}")
    @ResponseStatus(HttpStatus.OK)
    public int GetPlaylistIdByName(@PathVariable String name)  {
        return playListService.GetPlaylistIdByName(name);
    }
    @PostMapping(value = "/CreatePlaylist")
    @ResponseStatus(HttpStatus.CREATED)
    public void CreatePlaylist( @RequestBody String name)  {
        playListService.CreatePlaylist(name);
    }
    @PostMapping(value = "/AddSong/{playListId}/{songId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void AddSong( @RequestBody int playListId, @RequestBody String songId) throws JsonProcessingException {
        playListService.AddSong(playListId,songId);
    }

    @DeleteMapping("/RemoveSong/{playListId}/{songId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void RemoveSong(@PathVariable int playListId, @PathVariable String songId) throws SongException {
        playListService.RemoveSong(playListId,songId);
    }

    @PutMapping("/ClearPlayList/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void ClearPlayList(@RequestBody int id) throws SongException {
        playListService.ClearPlaylist(id);
    }

}
