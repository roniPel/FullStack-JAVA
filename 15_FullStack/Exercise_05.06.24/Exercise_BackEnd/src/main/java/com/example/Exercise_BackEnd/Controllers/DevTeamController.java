package com.example.Exercise_BackEnd.Controllers;

import com.example.Exercise_BackEnd.Beans.DevTeam;
import com.example.Exercise_BackEnd.Exceptions.MeetingException;
import com.example.Exercise_BackEnd.Services.DevTeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/group")
@CrossOrigin
public class DevTeamController {
    private final DevTeamService teamService;

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<DevTeam> getAllTeams(){
        return teamService.AllDevTeams();
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public void addTeam(@RequestBody DevTeam devTeam) throws MeetingException {
        teamService.AddTeam(devTeam);
    }
}
