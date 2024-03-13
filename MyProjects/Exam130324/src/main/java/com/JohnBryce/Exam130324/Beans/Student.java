package com.JohnBryce.Exam130324.Beans;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Date birthday;

    private Boolean isActive;

    @OneToMany(cascade = CascadeType.ALL,
            orphanRemoval = true)
    @Singular
    private List<Grade> grades;
}
