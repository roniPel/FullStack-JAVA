package com.mindali.songs.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mindali.songs.beans.YoutubeData;
import com.mindali.songs.services.SongService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/Songs")
@RequiredArgsConstructor
public class SongController {
    private final SongService songService;

    @GetMapping("/GetOneSong/{id}")
    @ResponseStatus(HttpStatus.OK)
    public YoutubeData GetOneSong(@PathVariable String id) throws JsonProcessingException {
        return songService.GetSongData(id);
    }
}
