package com.example.Exercise_BackEnd.Controllers;

import com.example.Exercise_BackEnd.Beans.DevTeam;
import com.example.Exercise_BackEnd.Beans.Meeting;
import com.example.Exercise_BackEnd.Exceptions.MeetingException;
import com.example.Exercise_BackEnd.Services.DevTeamService;
import com.example.Exercise_BackEnd.Services.MeetingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/meetings")
@CrossOrigin
public class MeetingController {
    private final MeetingService meetingService;
    private final DevTeamService teamService;

    @PostMapping("/addMeeting")
    @ResponseStatus(HttpStatus.CREATED)
    public void addMeeting(@RequestBody Meeting meeting) throws MeetingException {
        meetingService.AddMeeting(meeting);
    }

    @PostMapping("/addTeam")
    @ResponseStatus(HttpStatus.CREATED)
    public void addTeam(@RequestBody DevTeam devTeam) throws MeetingException {
        meetingService.AddDevTeam(devTeam);
    }

    @GetMapping("/allMeetings")
    public List<Meeting> getAllMeetings(){
        return meetingService.GetAllMeetings();
    }

    @GetMapping("/byTeam/{id}")
    public List<Meeting> getMeetingByTeam(@PathVariable int id){
        return meetingService.MeetingsByDevTeam(id);
    }

    @GetMapping("/allTeams")
    public List<DevTeam> getAllTeams(){
        return teamService.AllDevTeams();
    }
}
