package com.example.Exercise_BackEnd.Beans;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name="meetings")
@Builder
public class Meeting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int meetingId;
//    @Column(table = "teams",
//            nullable = false)
    private Integer devId;
    //private DevTeam devTeam;
    private String startTime;
    private String endTime;
    private String description;
    private String roomName;
}
