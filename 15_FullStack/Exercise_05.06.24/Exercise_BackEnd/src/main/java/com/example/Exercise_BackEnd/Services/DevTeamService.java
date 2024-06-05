package com.example.Exercise_BackEnd.Services;

import com.example.Exercise_BackEnd.Beans.DevTeam;
import com.example.Exercise_BackEnd.Exceptions.Errors;
import com.example.Exercise_BackEnd.Exceptions.MeetingException;
import com.example.Exercise_BackEnd.Repositories.DevTeamRepository;
import com.example.Exercise_BackEnd.Repositories.MeetingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DevTeamService {
    private final DevTeamRepository teamRepo;

    public List<DevTeam> AllDevTeams(){
        return teamRepo.findAll();
    }

    public boolean AddTeam(DevTeam devTeam) throws MeetingException {
        if(teamRepo.existsById(devTeam.getId())){
            throw new MeetingException(Errors.TEAM_ALREADY_EXISTS);
        }
        teamRepo.save(devTeam);
        return true;
    }
}
