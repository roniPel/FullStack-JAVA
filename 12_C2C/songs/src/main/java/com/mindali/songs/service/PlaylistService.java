package com.mindali.songs.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mindali.songs.advice.RestError;
import com.mindali.songs.beans.PlayList;
import com.mindali.songs.beans.YoutubeData;
import com.mindali.songs.exceptions.SongErrors;
import com.mindali.songs.exceptions.SongException;
import com.mindali.songs.repository.PlaylistRepository;
import com.mindali.songs.repository.YouTubeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlaylistService {
    private final YouTubeRepository youTubeRepo;
    private final PlaylistRepository playlistRepo;
    private final SongService songService;
    public boolean CreatePlaylist(String name) {
        //Check if playlist exists
        if(playlistRepo.existsByPlaylistName(name)) {
            return false;
        }
        PlayList playList = PlayList.builder()
                .playlistName(name)
                .songList(null)
                .build();
        playlistRepo.save(playList);
        return true;
    }

    public boolean UpdatePlaylist(PlayList playlist) throws SongException {
        if(playlistRepo.existsById(playlist.getId())) {
            playlistRepo.save(playlist);
            return true;
        }
        return false;
    }

    public boolean ClearPlaylist(int id) throws SongException {
        if(playlistRepo.existsById(id)) {
            PlayList playlist = playlistRepo.findById(id).get();
            playlist.setSongList(null);
            playlistRepo.save(playlist);
            return true;
        }
        return false;
    }

    public boolean AddSong(int playListId, String songId) throws JsonProcessingException {
        if (playlistRepo.existsById(playListId)){
            PlayList playList = playlistRepo.findById(playListId).get();
            List<YoutubeData> myList = playList.getSongList();
            myList.add(songService.getSongData(songId));
            playlistRepo.saveAndFlush(playList);
            return true;
        }
        return false;
    }

    public boolean RemoveSong(int playlistId, String songId)  {
        if (playlistRepo.existsById(playlistId)){
            PlayList playList = playlistRepo.findById(playlistId).get();
            playList.getSongList().remove(youTubeRepo.findById(songId).orElse(null));
            playlistRepo.save(playList);
            return true;
        }
        return false;
    }

    public int GetPlaylistIdByName(String name)  {
        return playlistRepo.findByPlaylistName(name).getId();
    }

}
