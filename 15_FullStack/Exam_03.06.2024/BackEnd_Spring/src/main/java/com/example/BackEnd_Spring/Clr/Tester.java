package com.example.BackEnd_Spring.Clr;

import com.example.BackEnd_Spring.Beans.Person;
import com.example.BackEnd_Spring.Beans.Task;
import com.example.BackEnd_Spring.Repositories.TaskRepository;
import com.example.BackEnd_Spring.Services.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

//@Component
@Order(1)
@RequiredArgsConstructor
public class Tester implements CommandLineRunner {
    private final TaskService taskService;
    @Override
    public void run(String... args) throws Exception {
        try{
            System.out.println("AddTask");
            // Create people
            Person person4 = Person.builder()
                    .name("Person4")
                    .phoneNum(1111111111L)
                    .build();
            Person person5 = Person.builder()
                    .name("Person5")
                    .phoneNum(2222222222L)
                    .build();
            Person person6 = Person.builder()
                    .name("Person6")
                    .phoneNum(333333333L)
                    .build();


            // Create tasks
            Task task4 = Task.builder()
                    .name("Task4")
                    .responsible(person4)
                    .scheduledDate(LocalDate.of(2024,6,15))
                    .isCompleted(false)
                    .build();
            Task task5 = Task.builder()
                    .name("Task5")
                    .responsible(person5)
                    .scheduledDate(LocalDate.of(2024,8,22))
                    .isCompleted(false)
                    .build();
            Task task6 = Task.builder()
                    .name("Task6")
                    .responsible(person6)
                    .scheduledDate(LocalDate.of(2025,1,2))
                    .isCompleted(false)
                    .build();


            taskService.AddTask(task4);
            taskService.AddTask(task5);
            taskService.AddTask(task6);
            System.out.println("---------------------------------");

            System.out.println("DeleteTask");
            //taskService.DeleteTask(2);

            System.out.println("---------------------------------");
            System.out.println("All Tasks");
            System.out.println(taskService.GetAllTasks());

            System.out.println("---------------------------------");


        } catch(Exception exception){
            System.out.println(exception);
        }
    }
}
