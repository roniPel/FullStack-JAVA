package com.mindali.songs.repositories;

import com.mindali.songs.beans.YoutubeData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface YouTubeRepo extends JpaRepository<YoutubeData,String> {

}
