package com.example.ClassExercise_070224.AOP;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Aspect
@Component
public class LoggerAspect {
    @Before("@annotation(Logger)")
    public void currentTime() {
        System.out.println("Got you!");
        System.out.println(LocalDateTime.now());
    }
}
