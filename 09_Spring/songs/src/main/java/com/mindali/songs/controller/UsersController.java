package com.mindali.songs.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mindali.songs.beans.UserDetails;
import com.mindali.songs.beans.YoutubeData;
import com.mindali.songs.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("Users/")
@RequiredArgsConstructor
public class UsersController {
    private final UsersService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addUserDetails(@RequestBody UserDetails userDetails) throws Exception {
        userService.AddUserDetails(userDetails);
    }

}
