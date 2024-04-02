package com.mindali.songs.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mindali.songs.beans.YoutubeData;
import com.mindali.songs.exceptions.SongErrors;
import com.mindali.songs.exceptions.SongException;
import com.mindali.songs.repository.YouTubeRepository;
import jakarta.persistence.Cacheable;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SongService {
    private final RestTemplate restTemplate;
    private final YouTubeRepository youtubeRepo;
    private String URL = "https://www.googleapis.com/youtube/v3/videos?part=snippet&key=AIzaSyB_rfFikBTjch9lqoaaLuqX_ETqsGGTvN8&id=";

    public YoutubeData getSongData(String id) throws JsonProcessingException, SongException {
        //check if we have the data already
        if (youtubeRepo.existsById(id)){
            return youtubeRepo.findById(id).get();
        } else {
            return getYouTubeData(id);
        }

    }

    private YoutubeData getYouTubeData(String id) throws JsonProcessingException {
        //get all meta data from google as string
        String myMetaData = restTemplate.getForObject(URL+id,String.class);
        //use object mapper , to map our item in the json object
        ObjectMapper mapper = new ObjectMapper();
        //get snippet data, by using items field, and since it's array, get first data (0)
        var myData = mapper.readTree(myMetaData).get("items").get(0).get("snippet");
        //create an entity
        YoutubeData youtubeData = YoutubeData.builder()
                .id(id)
                .name(myData.get("title").asText())
                .imageURL(myData.get("thumbnails").get("standard").get("url").asText())
                .build();
        //save the data (cache)
        String desc = myData.get("description").asText();
        youtubeData.setDescription(desc.length()>200?desc.substring(0,200):desc);
        youtubeRepo.save(youtubeData);

        //create new bean of YouTubeData -> name,description, image
        return youtubeData;
    }

    public List<YoutubeData> GetAllSongs() {
        return youtubeRepo.findAll();
    }
}
