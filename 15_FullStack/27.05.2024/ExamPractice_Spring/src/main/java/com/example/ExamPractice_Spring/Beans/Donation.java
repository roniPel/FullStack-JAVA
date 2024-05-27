package com.example.ExamPractice_Spring.Beans;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Donation {
    @Id
    private int id;
    private String name;
    private int amount;
    private String blessing;

}
