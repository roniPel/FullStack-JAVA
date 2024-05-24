package com.mindali.songs.service;

import com.mindali.songs.beans.Credentials;
import com.mindali.songs.beans.UserDetails;
import com.mindali.songs.repositories.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsersService {
    private final UserRepo userRepo;

    /*
    private int id;
    private String email;
    private String password;
    private UserType userType;
    private String tel;
    private String location;
    private String genre;
     */

    public boolean registerUser(UserDetails user) throws Exception{
        if (userRepo.existsById(user.getId())){
            throw new Exception("UserExists");
        }
        userRepo.save(user);
        return true;
    }

    public UserDetails loginUser(Credentials data) throws Exception {
        UserDetails userDetails = userRepo.findByEmailAndPassword(data.getEmail(),data.getPassword());
        System.out.println("backend data");
        System.out.println(userDetails);
        //throw new Exception("Who are you?");
        return userDetails;
    }
}
