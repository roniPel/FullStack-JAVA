package com.example.hwExamPractice_Spring.Clr;

import com.example.hwExamPractice_Spring.Beans.Task;
import com.example.hwExamPractice_Spring.Services.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;

//@Component
@Order(1)
@RequiredArgsConstructor
public class Tester implements CommandLineRunner {
    private final TaskService taskService;

    @Override
    public void run(String... args) throws Exception {
        try{
            // Add Task
            Task task3 = Task.builder()
                    .name("Task3")
                    .dateScheduled(LocalDate.of(2025,6,8))
                    .dateCompleted(null)
                    .responsible("Barak")
                    .isCompleted(false)
                    .build();

            Task task4 = Task.builder()
                    .name("Task4")
                    .dateScheduled(LocalDate.of(2024,8,10))
                    .dateCompleted(null)
                    .responsible("Roni")
                    .isCompleted(false)
                    .build();

            taskService.AddTask(task3);
            taskService.AddTask(task4);
            System.out.println("---------------------------------");

            // Report Completed
            taskService.ReportCompleted(task3);
            System.out.println("---------------------------------");

            // All Tasks
            taskService.AllTasks().forEach(System.out::println);
            System.out.println("---------------------------------");

            // Completed Tasks
            taskService.CompletedTasks().forEach(System.out::println);
            System.out.println("---------------------------------");

            // Uncompleted Tasks
            taskService.UncompletedTasks().forEach(System.out::println);
            System.out.println("---------------------------------");

            // Tasks completed before..
            taskService.TasksCompletedBefore(LocalDate.of(2024,10,14))
                    .forEach(System.out::println);
            System.out.println("---------------------------------");
        }
        catch(Exception exception){
            System.out.println(exception.getMessage());
        }
    }
}
