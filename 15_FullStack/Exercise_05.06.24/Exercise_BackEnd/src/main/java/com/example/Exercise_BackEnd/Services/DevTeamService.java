package com.example.Exercise_BackEnd.Services;

import com.example.Exercise_BackEnd.Beans.DevTeam;
import com.example.Exercise_BackEnd.Repositories.DevTeamRepository;
import com.example.Exercise_BackEnd.Repositories.MeetingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DevTeamService {
    private final MeetingRepository meetingRepo;
    private final DevTeamRepository teamRepo;

    public List<DevTeam> AllDevTeams(){
        return teamRepo.findAll();
    }

}
