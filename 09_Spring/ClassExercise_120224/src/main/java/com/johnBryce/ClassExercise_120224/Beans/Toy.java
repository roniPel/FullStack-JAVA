package com.johnBryce.ClassExercise_120224.Beans;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.stereotype.Component;

@Entity
@Data
@Table(name = "toys")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Toy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id = (int)(Math.random()*1000000000);

    @Column(nullable = false,length = 40)
    private String name;

    public Toy(String name) {
        this.name = name;
    }
}
