package com.example.Exercise_BackEnd.Clr;

import com.example.Exercise_BackEnd.Beans.DevTeam;
import com.example.Exercise_BackEnd.Beans.Meeting;
import com.example.Exercise_BackEnd.Beans.Room;
import com.example.Exercise_BackEnd.Repositories.DevTeamRepository;
import com.example.Exercise_BackEnd.Repositories.MeetingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Collection;

@Component
@Order(1)
@RequiredArgsConstructor
public class Tester implements CommandLineRunner {
    private final MeetingRepository meetingRepo;
    private final DevTeamRepository teamRepo;
    @Override
    public void run(String... args) throws Exception {
        try{
            Meeting meeting1 = Meeting.builder()
                    //.id(1)
                    .description("This and that")
                    .start_date(LocalDateTime.now())
                    .end_date(LocalDateTime.now().plusHours(2L))
                    .room("Room111")
                    .build();
            Meeting meeting2 = Meeting.builder()
                    //.id(1)
                    .description("That and them")
                    .start_date(LocalDateTime.now())
                    .end_date(LocalDateTime.now().plusHours(2L))
                    .room("Room2")
                    .build();
            DevTeam team1 = DevTeam.builder()
                    //.id(1)
                    .name("Team1")
                    .meeting(meeting1)
                    .build();
            DevTeam team2 = DevTeam.builder()
                    //.id(1)
                    .name("Team2")
                    .meeting(meeting2)
                    .build();
            System.out.println("My new meetings: ");
            System.out.println(meeting1);
            System.out.println(meeting2);
            meetingRepo.saveAndFlush(meeting1);
            meetingRepo.saveAndFlush(meeting2);
            teamRepo.saveAndFlush(team1);
            teamRepo.saveAndFlush(team2);

        } catch (Exception e){
            System.out.println(e);
        }
    }
}
