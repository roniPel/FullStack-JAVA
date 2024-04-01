package com.mindali.songs.repository;

import com.mindali.songs.beans.YoutubeData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface YouTubeRepository extends JpaRepository<YoutubeData,String> {

}
