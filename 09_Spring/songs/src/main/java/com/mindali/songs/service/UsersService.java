package com.mindali.songs.service;

import com.mindali.songs.advice.ErrorDetails;
import com.mindali.songs.advice.RestError;
import com.mindali.songs.beans.PlayList;
import com.mindali.songs.beans.UserDetails;
import com.mindali.songs.repositories.PlayListRepo;
import com.mindali.songs.repositories.UsersRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsersService {
    private final UsersRepo usersRepo;
    private final PlayListRepo playListRepo;

    public void AddUserDetails(UserDetails userDetails) throws Exception {
        if (usersRepo.existsById(userDetails.getId())){
            throw new Exception("User Already Exists!");
        }
        usersRepo.save(userDetails);
    }

    public PlayList CreatePlaylist(int userId, String playListName) {
        PlayList newPlayList = PlayList.builder()
                .playListName(playListName)
                .build();
        playListRepo.saveAndFlush(newPlayList);
        int id = playListRepo.findPlayListIdByName(playListName);
        return playListRepo.findById(id).get();
    }

    public List<PlayList> GetUserPlaylists(int userId) throws Exception {
        UserDetails userDetails = usersRepo.findById(userId).orElseThrow( ()->
                new Exception("No such user in the System!") );
        return userDetails.getPlayLists();
    }
}
