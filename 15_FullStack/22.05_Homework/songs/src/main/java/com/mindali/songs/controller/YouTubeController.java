package com.mindali.songs.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mindali.songs.beans.UserDetails;
import com.mindali.songs.beans.YoutubeData;
import com.mindali.songs.service.SongService;
import com.mindali.songs.utils.JWT;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
@CrossOrigin()
public class YouTubeController {
    private final JWT jwtUtil;
    private final SongService service;
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getSongData(@RequestHeader("Authorization") String jwt,@PathVariable String id) throws JsonProcessingException, Exception {
//        String userJWT = jwt.split(" ")[1];
//        if (jwtUtil.validateToken(userJWT)){
//            HttpHeaders headers = new HttpHeaders();
//            headers.set("Authorization", "Bearer "+jwtUtil.generateToken(userJWT));
//            System.out.println(headers);
//            return new ResponseEntity<>(service.getSongData(id),headers,HttpStatus.OK);
//        }
//        throw new Exception("token error");
        return new ResponseEntity<>(service.getSongData(id),getHeaders(jwt),HttpStatus.OK);
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getAllSongs(@RequestHeader("Authorization") String jwt) throws Exception{
//        //System.out.println("JWT:"+jwt);
//        String userJWT = jwt.split(" ")[1];
//        //System.out.println(userJWT);
//        if (jwtUtil.validateToken(userJWT)){
//            //System.out.println(service.getAllSongs());
//            HttpHeaders headers = new HttpHeaders();
//            headers.set("Authorization","Bearer "+jwtUtil.generateToken(userJWT));
//            return new ResponseEntity<>(service.getAllSongs(),headers,HttpStatus.OK);
//        }
//        throw new Exception("Invalid token");
        return new ResponseEntity<>(service.getAllSongs(),getHeaders(jwt),HttpStatus.OK);
    }

    @GetMapping("/add/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void addSong(@PathVariable String id) throws JsonProcessingException{
        //call the service

    }

    @DeleteMapping("/removeSong/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<?> removeSong(@PathVariable String id, @RequestHeader("Authorization") String jwt) throws Exception{
    //public void removeSong(@PathVariable String id) throws JsonProcessingException {
        return new ResponseEntity<>(service.removeYouTubeData(id),getHeaders(jwt),HttpStatus.OK);
    }
    private HttpHeaders getHeaders(String jwt){
        HttpHeaders headers = new HttpHeaders();
        String userJWT = jwt.split(" ")[1];
        if (jwtUtil.validateToken(userJWT)){
            headers.set("Authorization", "Bearer "+jwtUtil.generateToken(userJWT));
        }
        System.out.println("New Header: "+headers);
        return headers;
    }
}
