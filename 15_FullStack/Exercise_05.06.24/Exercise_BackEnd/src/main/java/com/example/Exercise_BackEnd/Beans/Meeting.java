package com.example.Exercise_BackEnd.Beans;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

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
    private Integer teamId;
    //private DevTeam devTeam;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String description;
    private String room;
}
