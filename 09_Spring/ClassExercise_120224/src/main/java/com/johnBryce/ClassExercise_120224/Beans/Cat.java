package com.johnBryce.ClassExercise_120224.Beans;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@Table(name = "cats")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Cat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    // (int)(Math.random()*1000000000)

    @Column(nullable = false,length = 40)
    private String name;

    private float weight;

    @Singular
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Toy> toys;
}
