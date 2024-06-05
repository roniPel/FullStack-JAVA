package com.example.Exercise_BackEnd.Beans;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "teams")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class DevTeam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String teamName;

//    @Singular
//    @OneToMany(cascade = CascadeType.ALL,
//            orphanRemoval = true)
//    @JoinColumn(name = "devteam_id")
//    private List<Meeting> meetings;
}
