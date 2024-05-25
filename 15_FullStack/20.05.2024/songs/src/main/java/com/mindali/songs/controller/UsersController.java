package com.mindali.songs.controller;

import com.mindali.songs.beans.UserDetails;
import com.mindali.songs.beans.UserLogin;
import com.mindali.songs.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin()
@RequestMapping("/user")
@RequiredArgsConstructor
public class UsersController {
    private final UsersService usersService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void registerUser(@RequestBody UserDetails data) throws Exception {
        usersService.registerUser(data);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public void loginUser(@RequestBody UserLogin data) throws Exception{
        usersService.loginUser(data);
    }
}
