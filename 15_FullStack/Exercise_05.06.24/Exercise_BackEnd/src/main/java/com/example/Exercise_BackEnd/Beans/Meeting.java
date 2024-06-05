package com.example.Exercise_BackEnd.Beans;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Meeting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "devteam_id",
            nullable = false)
    private Integer teamId;
    //private DevTeam devTeam;
    private LocalDateTime start_date;
    private LocalDateTime end_date;
    private String description;
    private String room;
}
