package com.mindali.songs.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mindali.songs.beans.YoutubeData;

public interface SongService {
    YoutubeData GetSongData(String id) throws JsonProcessingException;
}
