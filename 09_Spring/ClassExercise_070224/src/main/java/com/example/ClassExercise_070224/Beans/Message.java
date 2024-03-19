package com.example.ClassExercise_070224.Beans;

import com.example.ClassExercise_070224.AOP.Logger;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Qualifier("message")
public class Message {
    @Value("${subject}")
    String subject;

    @Logger
    public void info() {
        System.out.println(subject);
    }
}

