package com.mindali.songs.service;

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
}
