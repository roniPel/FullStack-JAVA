package com.mindali.songs.controller;

import com.mindali.songs.beans.Credentials;
import com.mindali.songs.beans.UserDetails;
import com.mindali.songs.service.UsersService;
import com.mindali.songs.utils.JWT;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin()
@RequestMapping("/user")
@RequiredArgsConstructor
public class UsersController {
    private final UsersService usersService;
    private final JWT jwt;
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void registerUser(@RequestBody UserDetails data) throws Exception {
        usersService.registerUser(data);
    }

//    @PostMapping("/login")
//    @ResponseStatus(HttpStatus.OK)
//    public Credentials loginUser(@RequestBody UserLogin data) throws Exception{
//        UserDetails userDetails = usersService.loginUser(data);
//        //return to frontend : email,name,token,userType
//        return new Credentials(userDetails.getEmail(),userDetails.getName(),userDetails.getUserType());
//    }


    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody Credentials user) throws Exception{
        HttpHeaders headers = new HttpHeaders();
        UserDetails userDetails = usersService.loginUser(user);
        headers.set("Authorization","Bearer "+jwt.generateToken(userDetails));
        return new ResponseEntity<>(true,headers,HttpStatus.OK);
    }
}




















