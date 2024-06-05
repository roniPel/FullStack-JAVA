package com.example.Exercise_BackEnd.Beans;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class DevTeam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
//    @Singular
//    @OneToMany(cascade = CascadeType.ALL,
//            orphanRemoval = true)
//    private List<Meeting> meetings;
}
