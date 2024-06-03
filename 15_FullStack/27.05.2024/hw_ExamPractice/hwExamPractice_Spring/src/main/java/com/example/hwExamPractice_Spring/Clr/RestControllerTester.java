package com.example.hwExamPractice_Spring.Clr;

import com.example.hwExamPractice_Spring.Beans.Task;
import com.example.hwExamPractice_Spring.Services.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.Arrays;

//@Component
@Order(2)
@RequiredArgsConstructor
public class RestControllerTester implements CommandLineRunner {
    private final RestTemplate restTemplate;

    @Override
    public void run(String... args) throws Exception {
        try{
            LocalDate date = LocalDate.of(2025,8,15);
            Task[] tasks = restTemplate.getForObject
                    ("http://localhost:8080/Tasks/taskCompletedBefore/"+date, Task[].class);
            System.out.println("Result from restTemplate: "+Arrays.stream(tasks).toList());
            // System.out.println(Arrays.stream(tasks).toList());
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }
}
