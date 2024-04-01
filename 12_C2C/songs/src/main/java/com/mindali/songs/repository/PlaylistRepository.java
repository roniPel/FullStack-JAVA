package com.mindali.songs.repository;

import com.mindali.songs.beans.PlayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaylistRepository extends JpaRepository<PlayList,Integer> {

    boolean existsByPlaylistName(String playlistName);
    PlayList findByPlaylistName(String playlistName);
}
