package com.mindali.songs.repositories;

import com.mindali.songs.beans.PlayList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayListRepo extends JpaRepository<PlayList, Integer> {
    public boolean existsByPlayListName(String playListName);
    public PlayList findByPlayListName(String playListName);
    //public boolean existById(boolean id);
}
