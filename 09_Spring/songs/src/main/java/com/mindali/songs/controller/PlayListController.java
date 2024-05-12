package com.mindali.songs.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mindali.songs.beans.PlayList;
import com.mindali.songs.beans.YoutubeData;
import com.mindali.songs.service.PlayListService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("PlayLists/")
@RequiredArgsConstructor
public class PlayListController {
    private final PlayListService playListService;

    @GetMapping("/{name}")
    @ResponseStatus(HttpStatus.OK)
    public PlayList getPlayList(@PathVariable String name) {
        return playListService.getPlaylistByName(name);
    }

    @GetMapping("/{name}")
    @ResponseStatus(HttpStatus.OK)
    public int getPlayListId(@PathVariable String name) {
        return playListService.getIdByPlayListName(name);
    }
}
