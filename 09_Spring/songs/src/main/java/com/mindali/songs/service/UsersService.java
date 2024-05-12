package com.mindali.songs.service;

import com.mindali.songs.advice.ErrorDetails;
import com.mindali.songs.beans.UserDetails;
import com.mindali.songs.repositories.UsersRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class UsersService {
    private final RestTemplate restTemplate;
    private final UsersRepo usersRepo;

    public void AddUserDetails(UserDetails userDetails) throws Exception {
        if (usersRepo.existsById(userDetails.getId())){
            throw new Exception("User Already Exists!");
        }
        usersRepo.save(userDetails);
    }
}
