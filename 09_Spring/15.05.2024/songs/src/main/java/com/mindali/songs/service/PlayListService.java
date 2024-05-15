package com.mindali.songs.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mindali.songs.beans.PlayList;
import com.mindali.songs.beans.YoutubeData;
import com.mindali.songs.repositories.PlayListRepo;
import com.mindali.songs.repositories.YouTubeRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class PlayListService {
    private final PlayListRepo playListRepo;
    private final SongService songService;


    public boolean createPlayList(String name){
        if (playListRepo.existsByPlayListName(name)){
            return false;
        }
        PlayList playList = PlayList.builder()
                .playListName(name)  //זה ההברקות של אורי
                .build();
        playListRepo.save(playList);
        return true;
    }

    //add,remove,reset
    public boolean addSong(int playListId, String songId) throws JsonProcessingException {
        if (playListRepo.existsById(playListId)){
            PlayList playList = playListRepo.findById(playListId).get();
            List<YoutubeData> myList = playList.getMyList();
            myList.add(songService.getSongData(songId));
            playListRepo.saveAndFlush(playList);
            return true;
        }
        return false;
    }

    public boolean removeSong(int playListId,String songId) throws JsonProcessingException {
        if (playListRepo.existsById(playListId)){
//            PlayList playList = playListRepo.findById(playListId).get();
//            List<YoutubeData> myList = playList
//                    .getMyList().stream()
//                    .filter(item-> !Objects.equals(item.getId(), songId)).
//                    toList();
//            playList.setMyList(myList);
//            playListRepo.saveAndFlush(playList);

//            PlayList playList=playListRepo.findById(playListId).get();
//            List<YoutubeData> myList = playList.getMyList();
//            myList.removeIf(item->item.getId().equals(songId));
//            playListRepo.save(playList);
//            return true;

            PlayList playList=playListRepo.findById(playListId).get();
            List<YoutubeData> myList = playList.getMyList();
            myList.remove(songService.getSongData(songId));
            playListRepo.saveAndFlush(playList);
            return true;

//                PlayList playList=playListRepo.findById(playListId).get();
//                List<YoutubeData> mySongs = playList.getMyList();
//                int index = mySongs.indexOf(songService.getSongData(songId)); //O(n)
//                mySongs.set(index,null);
//                playListRepo.saveAndFlush(playList);
//                return true;

        }
        return false;
    }

    public boolean emptyPlayList(int id){
        //check if song list exists....
        if (playListRepo.existsById(id)){
            PlayList playList = playListRepo.findById(id).get();
            playList.setMyList(null);
            //playList.getMyList().clear();
            playListRepo.save(playList);
            return true;
        }
        return false;
    }

    public int getIdByPlayListName(String name){
        return (playListRepo.findByPlayListName(name).getId());
    }

}
