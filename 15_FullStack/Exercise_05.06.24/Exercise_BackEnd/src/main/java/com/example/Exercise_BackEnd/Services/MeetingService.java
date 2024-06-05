package com.example.Exercise_BackEnd.Services;

import com.example.Exercise_BackEnd.Beans.DevTeam;
import com.example.Exercise_BackEnd.Beans.Meeting;
import com.example.Exercise_BackEnd.Exceptions.Errors;
import com.example.Exercise_BackEnd.Exceptions.MeetingException;
import com.example.Exercise_BackEnd.Repositories.DevTeamRepository;
import com.example.Exercise_BackEnd.Repositories.MeetingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MeetingService {
    private final MeetingRepository meetingRepo;
    private final DevTeamRepository teamRepo;

    public boolean AddMeeting(Meeting meeting) throws MeetingException {
        int meetingId = meeting.getMeetingId();
        if(meetingRepo.existsById(meetingId)){
            throw new MeetingException(Errors.MEETING_ALREADY_EXISTS);
        }
        // Check if DevTeam does not exist in DB
        if(!teamRepo.existsById(meeting.getTeamId())){
            throw new MeetingException(Errors.TEAM_DOES_NOT_EXIST);
        }
        meetingRepo.save(meeting);
        return true;
    }

    public List<Meeting> MeetingsByDevTeam(int devTeamId) {
        return meetingRepo.findAllByTeamId(devTeamId);
    }

    public List<Meeting> GetAllMeetings() {
        return meetingRepo.findAll();
    }
}
