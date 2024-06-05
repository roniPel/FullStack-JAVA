package com.example.Exercise_BackEnd.Clr;

import com.example.Exercise_BackEnd.Beans.DevTeam;
import com.example.Exercise_BackEnd.Beans.Meeting;
import com.example.Exercise_BackEnd.Repositories.DevTeamRepository;
import com.example.Exercise_BackEnd.Repositories.MeetingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

//@Component
@Order(1)
@RequiredArgsConstructor
public class Tester implements CommandLineRunner {
    private final MeetingRepository meetingRepo;
    private final DevTeamRepository teamRepo;
    @Override
    public void run(String... args) throws Exception {
        try{
            DevTeam team1 = DevTeam.builder()
                    //.id(1)
                    .teamName("Team1")
                    .build();
            DevTeam team2 = DevTeam.builder()
                    //.id(1)
                    .teamName("Team2")
                    .build();
            Meeting meeting1 = Meeting.builder()
                    //.id(1)
                    .devId(1)
                    .description("This and that")
                    .startTime("10:30")
                    .endTime("11:00")
                    .roomName("Room111")
                    .build();
            Meeting meeting2 = Meeting.builder()
                    //.id(1)
                    .devId(2)
                    .description("That and them")
                    .startTime("10:30")
                    .endTime("11:00")
                    .roomName("Room2")
                    .build();
            Meeting meeting3 = Meeting.builder()
                    //.id(1)
                    .devId(2)
                    .description("Another Meeting for you")
                    .startTime("10:30")
                    .endTime("11:00")
                    .roomName("Room3")
                    .build();

            teamRepo.saveAndFlush(team1);
            teamRepo.saveAndFlush(team2);
            meetingRepo.saveAndFlush(meeting1);
            meetingRepo.saveAndFlush(meeting2);
            System.out.println("My new meetings: ");
            System.out.println(meeting1);
            System.out.println(meeting2);

        } catch (Exception e){
            System.out.println(e);
        }
    }
}
