package com.mindali.songs.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mindali.songs.beans.YoutubeData;
import com.mindali.songs.repositories.YouTubeRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SongService {
    private final RestTemplate restTemplate;
    private final YouTubeRepo youtubeRepo;
    private String URL = "https://www.googleapis.com/youtube/v3/videos?part=snippet&key=AIzaSyB_rfFikBTjch9lqoaaLuqX_ETqsGGTvN8&id=";

    public YoutubeData getSongData(String id) throws JsonProcessingException {
        //check if we have the data already
        //return youtubeRepo.findById(id).orElse(getYouTubeData(id));
        if (youtubeRepo.existsById(id)){
            System.out.println("we have the song...");
           return youtubeRepo.findById(id).get();
        } else {
            System.out.println("we don't have the song....");
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
                .timeStamp(new Date(System.currentTimeMillis()))
                .build();
        //save the data (cache)
        String desc = myData.get("description").asText();
        youtubeData.setDescription(desc.length()>200?desc.substring(0,200):desc);
        youtubeRepo.save(youtubeData);

        System.out.println("Create new instance for cache");

        //create new bean of YouTubeData -> name,description, image
        return youtubeData;
    }

    public List<YoutubeData> getAllSongs(){
        return youtubeRepo.findAll();
    }
}
