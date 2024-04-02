package com.mindali.songs.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mindali.songs.exceptions.SongException;
import com.mindali.songs.service.PlaylistService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
    public void AddSong(@PathVariable Integer playListId, @PathVariable String songId) throws JsonProcessingException, SongException {
        playListService.AddSong(playListId,songId);
    }

    @DeleteMapping("/RemoveSong/{playListId}/{songId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void RemoveSong(@PathVariable Integer playListId, @PathVariable String songId) throws SongException {
        playListService.RemoveSong(playListId,songId);
    }

    @PostMapping(value = "/ClearPlayList/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void ClearPlayList(@PathVariable int id) throws SongException {
        playListService.ClearPlaylist(id);
    }

}
